package api;


import io.restassured.http.ContentType;

import java.util.Map;

//Defines basic struture of API, which needs to defined while creating API

public interface IApiRequest<T> {

    public T requestBody();

    public ApiBuilder.MethodType apiMethod();

    public Map headers();

    public String basePath();

    public String baseUrl();

    public ContentType contentType();

    public Map<String, ?> queryParam();

    public T build();

}
