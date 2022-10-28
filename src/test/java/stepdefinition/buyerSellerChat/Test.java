package stepdefinition.buyerSellerChat;

import api.ApiExecutor;
import api.ApiResponse;
import apiRequest.moengage.SendPushRequest;
import apiRequest.quickblox.ChatDialogRequest;
import apiRequest.quickblox.SessionRequest;
import apiRequest.quickblox.UnreadCount;
import enums.ChatUserEnums;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

public class Test {

    @org.testng.annotations.Test
    public void test(){

//        sendPushRequest.setContext("signature", "abv");
//        System.out.println("appId :" + sendPushRequest.getContext().get("appId"));
//        System.out.println("campaignName :" + sendPushRequest.getContext().get("campaignName") );
//        System.out.println("key is:" + "1ZEOTQCQ2GM4");
//        sendPushRequest.setKeyValue("category1", "abc");
//        sendPushRequest.setKeyValue("category2","xyz");
//        sessionRequest.setSignature("0c82d45b-c99b-4e9b-ab5f-cda2393b1bb4");
//         new ApiExecutor(sessionRequest)
//                .execute()
//                 .validatableResponse()
//                 .statusCode(201);
        SessionRequest.createSession(ChatUserEnums.RAMAN_SELLER.value());
//        UnreadCount unreadCount = new UnreadCount();
//        System.out.println(unreadCount.getUnreadCount());
        ChatDialogRequest chatDialogRequest = new ChatDialogRequest();
        chatDialogRequest.getChatDialogMessages();


    }
}
