package api;

import io.restassured.response.Response;

import java.util.Map;

public interface IApiResponse<T> {

    public T body();

    public String content();

    public int statusCode();

    public Response response();

    public Map headers();

}
