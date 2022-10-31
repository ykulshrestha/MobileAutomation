package api;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import io.restassured.builder.ResponseSpecBuilder;

import java.util.HashMap;
import java.util.Map;

public final class ApiResponse implements IApiResponse {

    private Response response = null;

    public ApiResponse(Response response) {
        this.response = response;
    }

    public final static ResponseSpecification SUCCESS() {
        return new ResponseSpecBuilder()
                .expectBody("status", Matchers.equalToIgnoringCase("SUCCESS"))
                .build();
    }

    @Override
    public ResponseBody body() {
        return this.response.body();
    }

    @Override
    public String content() {
        return this.response.contentType();
    }

    @Override
    public int statusCode() {
        return this.response.statusCode();
    }

    @Override
    public Response response() {
        return this.response;
    }

    @Override
    public Map<String, String> headers() {
        Map<String, String> s = new HashMap<>();
        this.response.headers().asList().
                forEach(header -> {
                    s.put(header.getName(), header.getValue());
                });
        return s;
    }

    public ValidatableResponse validatableResponse() {
        return this.response.then();
    }
}
