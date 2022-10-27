package apiRequest.quickblox;

import api.ApiBuilder;
import api.ApiRequest;
import api.ApiResponse;
import configs.MoengageConfig;
import configs.QuickbloxConfig;
import io.cucumber.java.it.Ma;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.Map;

public class SessionRequest extends ApiRequest {

    public SessionRequest() {
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
        return ApiBuilder.MethodType.POST;
    }

    @Override
    public String basePath() {
        return "/session.json";
    }

    @Override
    public ContentType contentType() {
        return ContentType.JSON;
    }

    @Override
    protected String requestTemplatePath() {
        return "quickblox/session.json";
    }

    public void setSignature(){
        Map<String, String> quicbloxMap = QuickbloxConfig.readQuickBloxProperties();
        String nonce = String.valueOf(Math.random());
        String timeStamp = String.valueOf(System.currentTimeMillis()/1000L);
        String normalLizedString = "application_id="+quicbloxMap.get("applicationId")
                + "&auth_key=" + quicbloxMap.get("authorizationKey")
                + "&nonce=" + nonce
                + "&timestamp=" + timeStamp;
        this.setContext("nonce", nonce);
        this.setContext("timestamp", timeStamp);
        this.setContext("signature", getSHA1(normalLizedString, quicbloxMap.get("authorizationSecret")));
    }
}

