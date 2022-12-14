package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public abstract class ApiRequest<T> implements IApiRequest<T> {

    private static final Logger LOGGER = LogManager.getLogger(ApiBuilder.class);
    private final String REQUEST_TEMPLATE_PATH_PREFIX = "src/main/resources/api/";
    protected Map<String, Object> header = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();
    private LinkedHashMap<String, Object> context = new LinkedHashMap<>();
    private LinkedList<String> deleteContext = new LinkedList<>();
    protected String baseUrl = "";
    public DocumentContext requestContext = null;
    private boolean isRequestLoaded = false;

    public ApiRequest() {
        jsonRequestTemplateLoader();
    }

    @Override
    public Map headers() {
        return header;
    }

    public ApiRequest<T> setHeaders(String key, Object value) {
        this.header.put(key, value);
        return this;
    }

    @Override
    public Map<String, ?> queryParam() {
        return queryParams;
    }

    public ApiRequest<T> setQueryParam(String key, String value) {
        this.queryParams.put(key, value);
        return this;
    }

    public final ApiRequest<T> deleteContext(String path) {
        if (deleteContext == null)
            deleteContext = new LinkedList<>();
        deleteContext.add(path);
        return this;
    }

    public final ApiRequest<T> setContext(String path, Object value) {
        if (context == null) {
            context = new LinkedHashMap<>();
        }
        context.put(path, value);
        return this;
    }

    public Map<String, ?> getContext() {
        return this.context;
    }

    protected T getLoadedData(String jsonPath) {
        jsonRequestTemplateLoader();
        return this.requestContext.read(jsonPath);
    }

    private void updateRequestContext() {
        if (requestContext == null) {
            LOGGER.info("No need to set context when baseTemplate not provided");
        } else {
            context.forEach((k, v) -> {
                String[] path = k.split("\\.");
                if (path != null && path.length > 0) {
                    StringJoiner trgtPath = new StringJoiner(".", "$.", "");
                    for (int i = 0; i < path.length - 1; i++) {
                        trgtPath.add(path[i]);
                    }
                    if (v instanceof List) {
                        JSONArray array = new ObjectMapper().convertValue(v, JSONArray.class);
                        if (trgtPath.toString().equalsIgnoreCase("$."))
                            requestContext.put("$", path[path.length - 1], v);
                        else
                            requestContext.put(trgtPath.toString(), path[path.length - 1], array);
                    } else if (trgtPath.toString().equalsIgnoreCase("$.")) {
                        requestContext.put("$", path[path.length - 1], v);
                    } else
                        requestContext.put(trgtPath.toString(), path[path.length - 1], v);
                }
            });
            deleteContext.forEach(p -> {
                String[] path = p.split("\\.");
                if (path != null && path.length > 0) {
                    StringJoiner trgtPath = new StringJoiner(".", "$.", "");
                    for (int i = 0; i < path.length - 1; i++) {
                        trgtPath.add(path[i]);
                    }
                    requestContext.delete(trgtPath.toString() + "." + path[path.length - 1]);
                }
            });
        }
    }

    private String getClassName(Object obj) {
        try {
            return obj.getClass().getName();
        } catch (Exception e) {
            return "";
        }
    }

    private void jsonRequestTemplateLoader() {
        if (!isRequestLoaded) {
            String requestTmpPath = requestTemplatePath();
            if (null != requestTmpPath) {
                if (!requestTmpPath.isEmpty()) {
                    String requestPath = REQUEST_TEMPLATE_PATH_PREFIX + requestTmpPath;
                    if (!requestPath.contains("reportportal"))
                        LOGGER.info("loading request: " + requestPath);
                    ClassLoader cl = getClass().getClassLoader();
                    URL resource = cl.getResource(requestPath);
                    try {
                        requestContext = JsonPath.parse(new File(requestPath));
                        isRequestLoaded = true;
                    } catch (IOException e) {
                        LOGGER.error("Error occurred while loading request: " + requestPath, e);
                    }
                }
            }
        }
    }


    public void loadRequest() {
        jsonRequestTemplateLoader();
    }

    @Override
    public String baseUrl() {
        return this.baseUrl;
    }

    public ApiRequest<T> setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    @Override
    public final T build() {
        updateRequestContext();
        if (null == requestContext) {
            return null;
        }
        return requestContext.json();
    }

    protected abstract String requestTemplatePath();

    protected String getSHA256(String input){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        BigInteger number = new BigInteger(1, md.digest(input.getBytes(StandardCharsets.UTF_8)));
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    protected String getSHA1(String data, String key)
    {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        try {
            mac.init(signingKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = mac.doFinal(data.getBytes());
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }
}

