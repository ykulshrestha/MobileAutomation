package apiRequest.quickblox;

import api.ApiBuilder;
import api.ApiExecutor;
import api.ApiRequest;
import apiResponse.messageResponse.MessageResponse;
import configs.QuickbloxConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;

import java.util.Map;
import java.util.Objects;

public class deleteUserRequest extends ApiRequest {

    String userId;

    public deleteUserRequest(String userId) {
        this.userId = userId;
        this.baseUrl = QuickbloxConfig.readQuickBloxProperties().get("baseUrl");
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
        return ApiBuilder.MethodType.DELETE;
    }

    @Override
    public String basePath() {
        return "/users/" + userId + ".json";
    }

    @Override
    public ContentType contentType() {
        return ContentType.JSON;
    }

    @Override
    protected String requestTemplatePath() {
        return null;
    }

    public void addQbToken(){
        if (Objects.isNull(SessionRequest.getSession()))
            throw new RuntimeException("session is not created");
        this.setHeaders("QB-Token", SessionRequest.getSession());
    }

    public void deleteUser(){
        addQbToken();
        new ApiExecutor(this)
                .execute().
                validatableResponse()
                .statusCode(HttpStatus.SC_OK);

    }

}
