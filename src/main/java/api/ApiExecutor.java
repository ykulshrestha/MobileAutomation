package api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.awaitility.Duration;
import org.awaitility.core.ConditionTimeoutException;
import org.awaitility.core.Predicate;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import static org.awaitility.Awaitility.with;

public class ApiExecutor implements IApi {
    private static final Logger LOGGER = LogManager.getLogger(ApiBuilder.class);
    private ApiBuilder baseApi = null;
    private Response response = null;

    public ApiExecutor(IApiRequest request) {
        Object reqBody = null;
        if (request.requestBody() == null) {
            reqBody = request.build();
        } else
            reqBody = request.requestBody();

        ApiBuilder api = new ApiBuilder();
        api.setMethod(request.apiMethod());
        api.getRequestSpecBuilder().setContentType(request.contentType());
        api.getRequestSpecBuilder().setBaseUri(request.baseUrl());
        api.getRequestSpecBuilder().setBasePath(request.basePath());
        if(request.headers() !=null)
            api.getRequestSpecBuilder().addHeaders(request.headers());
        if (reqBody != null)
            api.getRequestSpecBuilder().setBody(reqBody);
        api.getRequestSpecBuilder().addQueryParams(request.queryParam());
        this.baseApi = api;

    }


    @Override
    public ApiResponse execute() {

        this.response = this.baseApi.execute();
        LOGGER.info(this.response.body().prettyPrint());
        return new ApiResponse(this.response);
    }

    public ApiResponse execute_waitAndPoll(Duration maxDuration, Duration pollInterval, Duration pollDelay, Predicate<ApiResponse> responsePredicate) {

        try {
            return with().pollInSameThread().await()
                    .atMost(maxDuration)
                    .pollInterval(pollInterval)
                    .pollDelay(pollDelay)
                    .ignoreException(NullPointerException.class)
                    .ignoreException(IllegalArgumentException.class)
                    .until(checkStatus(), responsePredicate);
        } catch (ConditionTimeoutException e) {
            LOGGER.error("Provided condition not matched with API response", e);
            Assertions.fail("Provided condition not matched with API response", e);
        } catch (Exception e){
            LOGGER.error("Provided condition not matched with API response", e);
            Assertions.fail("Provided condition not matched with API response", e);
        }
        return null;
    }


    private Callable<ApiResponse> checkStatus() {
        LOGGER.info("Starting api polling model ........");
        final int[] counter = {0};
        return new Callable<ApiResponse>() {
            @Override
            public ApiResponse call() throws Exception {
                LOGGER.info("api polling count: " + counter[0]++);
                return execute();
            }
        };
    }

    public ApiResponse execute_waitAndPoll(Predicate<ApiResponse> responsePredicate) {
        return execute_waitAndPoll(Duration.TWO_MINUTES, Duration.TWO_SECONDS, Duration.ONE_SECOND, responsePredicate);
    }


    public Response getResponse() {
        return this.response;
    }

}
