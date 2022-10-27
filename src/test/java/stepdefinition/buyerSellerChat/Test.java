package stepdefinition.buyerSellerChat;

import api.ApiExecutor;
import api.ApiResponse;
import apiRequest.moengage.SendPushRequest;
import apiRequest.quickblox.SessionRequest;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class Test {

    @org.testng.annotations.Test
    public void test(){
        SessionRequest sessionRequest = new SessionRequest();

//        sendPushRequest.setContext("signature", "abv");
//        System.out.println("appId :" + sendPushRequest.getContext().get("appId"));
//        System.out.println("campaignName :" + sendPushRequest.getContext().get("campaignName") );
//        System.out.println("key is:" + "1ZEOTQCQ2GM4");
//        sendPushRequest.setKeyValue("category1", "abc");
//        sendPushRequest.setKeyValue("category2","xyz");
        sessionRequest.setSignature();
         new ApiExecutor(sessionRequest)
                .execute()
                 .validatableResponse()
                 .statusCode(201);


    }
}
