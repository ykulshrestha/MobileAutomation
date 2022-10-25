package stepdefinition.buyerSellerChat;

import api.ApiExecutor;
import api.ApiResponse;
import apiRequest.moengage.SendPushRequest;

public class Test {

    @org.testng.annotations.Test
    public void test(){
        SendPushRequest sendPushRequest = new SendPushRequest();

        new ApiExecutor(sendPushRequest)
                .execute()
                .validatableResponse()
                .statusCode(200)
                .spec(ApiResponse.SUCCESS());
    }
}
