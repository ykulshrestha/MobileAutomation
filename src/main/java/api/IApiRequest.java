package api;


import io.restassured.http.ContentType;

import java.util.Map;

/**
 * Defines basic struture of API, which needs to defined while creating API
 *
 * @param <T>
 */
public interface IApiRequest<T> {

    /**
     * Implementation can used like: <br>
     * - method can return the request as Java DTO <br>
     * <p>
     * - method can also used to set the base request param setup <br>
     * <p>
     * - Priority of setting request param is 1 and can override other value of same key. <br>
     * - Use setContext on this method only when KEY:VALUE is constant
     *
     * @return Generic
     */
    public T requestBody();

    public ApiBuilder.MethodType apiMethod();

    public Map headers();

    public String basePath();

    public String baseUrl();

    public ContentType contentType();

    public Map<String, ?> queryParam();

    public T build();

}
