package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public abstract class ApiRequest<T> implements IApiRequest<T> {

    private static final Logger LOGGER = LogManager.getLogger(ApiBuilder.class);
    private final String REQUEST_TEMPLATE_PATH_PREFIX = "api";
    protected Map<String, Object> header = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();
    private LinkedHashMap<String, Object> context = new LinkedHashMap<>();
    private LinkedList<String> deleteContext = new LinkedList<>();
    protected String baseUrl = "";
    private DocumentContext requestContext = null;
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
                    } else if (getClassName(v).contains("com.paytm") && !v.getClass().isEnum()) {
                        JSONObject object = new ObjectMapper().convertValue(v, JSONObject.class);
                        if (trgtPath.toString().equalsIgnoreCase("$."))
                            requestContext.put("$", path[path.length - 1], v);
                        else
                            requestContext.put(trgtPath.toString(), path[path.length - 1], object);
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
                        requestContext = JsonPath.parse(resource);
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
}

