package apiRequest.quickblox;

import api.ApiBuilder;
import api.ApiExecutor;
import api.ApiRequest;
import configs.QuickbloxConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;

import java.util.Map;
import java.util.Objects;

public class UnreadCount extends ApiRequest {

    public UnreadCount() {
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
        return ApiBuilder.MethodType.GET;
    }

    @Override
    public String basePath() {
        return "chat/Message/unread.json";
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

    public int getUnreadCount(){
        addQbToken();
        JsonPath response = new ApiExecutor(this)
                .execute()
                .validatableResponse()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath();

        return Integer.parseInt(response.getString("total"));
    }

}
