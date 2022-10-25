package apiRequest.moengage;

import api.ApiBuilder;
import api.ApiRequest;
import io.restassured.http.ContentType;

import java.util.Map;

public class SendPushRequest extends ApiRequest {

    public SendPushRequest() {
        this.baseUrl = "https://pushapi-01.moengage.com";
    }

    @Override
    public Object requestBody() {
        return null;
    }

    @Override
    public Map headers() {
        return this.header;
    }

    @Override
    public ApiBuilder.MethodType apiMethod() {
        return ApiBuilder.MethodType.POST;
    }

    @Override
    public String basePath() {
        return "/v2/transaction/sendpush";
    }

    @Override
    public ContentType contentType() {
        return ContentType.JSON;
    }

    @Override
    protected String requestTemplatePath() {
        return "/moengage/sendPush.json";
    }

}
