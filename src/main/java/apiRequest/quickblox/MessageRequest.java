package apiRequest.quickblox;

import api.ApiBuilder;
import api.ApiExecutor;
import api.ApiRequest;
import apiResponse.messageResponse.MessageResponse;
import apiResponse.chatDialogResponse.ChatDialogResponse;
import com.google.gson.Gson;
import configs.QuickbloxConfig;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import java.util.Map;
import java.util.Objects;

public class MessageRequest extends ApiRequest {

    public MessageRequest() {
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
        return "chat/Message.json";
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

    public void addMessageQueryParams(String chatDialogId){
        this.setQueryParam("chat_dialog_id", chatDialogId);
        this.setQueryParam("sort_desc", "date_sent");
        this.setQueryParam("limit","5");
    }


    public MessageResponse getMessages(String chatDialogId){
        addQbToken();
        addMessageQueryParams(chatDialogId);
        Gson gson = new Gson();

        String response = new ApiExecutor(this)
                .execute().
                validatableResponse()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
        return gson.fromJson(response
                , MessageResponse.class);
    }


}
