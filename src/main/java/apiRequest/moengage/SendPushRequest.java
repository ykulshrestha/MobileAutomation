package apiRequest.moengage;

import api.ApiBuilder;
import api.ApiRequest;
import api.ApiResponse;
import configs.MoengageConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.Map;

public class SendPushRequest extends ApiRequest {

    public SendPushRequest() {
        this.baseUrl = MoengageConfig.readMoengageProperties().get("baseUrl");
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
        return "moengage/sendPush.json";
    }

    public void setSignature(){
        String campaignName;
        if (this.getContext().get("campaignName") != null)
            campaignName = this.getContext().get("campaignName").toString();
        else
            campaignName = this.requestContext.read("campaignName").toString();
        String sha256 =  MoengageConfig.readMoengageProperties().get("appId") + "|" +
                 campaignName + "|" +
                MoengageConfig.readMoengageProperties().get("apiSecret");
        this.setContext("signature", getSHA256(sha256));
    }

    public void setCampaignName(String name){
            this.setContext("campaignName" , name);
    }

    public void setKeyValue(String key, String name){
        String keyPath = "payload.ANDROID.defaultAction.kvPairs." + key;
        this.setContext(keyPath , name);
    }

    public boolean campaignPushSuccessfully(ApiResponse response){
        try {
            JsonPath jsonPath =  response.response().getBody().jsonPath();
            Assert.assertEquals(jsonPath.get("status"),"Success");
            return true;
              }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }



}
