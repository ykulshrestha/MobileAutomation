package apiRequest.quickblox;

import api.ApiBuilder;
import api.ApiExecutor;
import api.ApiRequest;
import apiResponse.chatDialogResponse.ChatDialogResponse;
import com.google.gson.Gson;
import configs.QuickbloxConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;

import java.util.Map;
import java.util.Objects;

//TODO: Connect with rohit/anand to ask about archive and normal inbox query params
public class ChatDialogRequest extends ApiRequest {
    public ChatDialogRequest() {
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
        return "chat/Dialog.json";
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

    public void addInboxQueryParams(){
        this.setQueryParam("data[class_name]", "HousingGroup");
        this.setQueryParam("data[isBuyerArchived][ne]", "true");
        this.setQueryParam("sort_desc", "last_message_date_sent");
        this.setQueryParam("last_message[ne]", "null");
        this.setQueryParam("limit", "100");
        this.setQueryParam("skip", "0");
    }

    public void addArchiveQueryParams(){
        this.setQueryParam("data[class_name]", "HousingGroup");
        this.setQueryParam("data[isSellerArchived]", "true");
        this.setQueryParam("sort_desc", "last_message_date_sent");
        this.setQueryParam("last_message[ne]", "null");
        this.setQueryParam("limit", "100");
        this.setQueryParam("skip", "0");
        this.setQueryParam("data[isChatDisabled][ne]", "true");
    }

     public ChatDialogResponse getChatDialogMessages(){
        addQbToken();
        addInboxQueryParams();
        Gson gson = new Gson();

        String response = new ApiExecutor(this)
                .execute().
                validatableResponse()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .asString();
         return gson.fromJson(response
                 , ChatDialogResponse.class);
     }



}
