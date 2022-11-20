package apiRequest.housing;

import api.ApiBuilder;
import api.ApiExecutor;
import api.ApiRequest;
import configs.HousingConfig;
import configs.MoengageConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class GetBulkBuyDetails extends ApiRequest {
    private static final Logger logger = LogManager.getLogger(GetBulkBuyDetails.class);


    public GetBulkBuyDetails() {
        this.baseUrl = HousingConfig.readHousingProperties().get("searchService");
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
        return "/api/v3/buy/get_bulk_buy_details";
    }

    @Override
    public ContentType contentType() {
        return ContentType.JSON;
    }

    @Override
    protected String requestTemplatePath() {
        return null;
    }

    public void addQueryParams(String type, String id){
        if (type.equalsIgnoreCase("resale"))
        this.setQueryParam("flat_ids", id+"_resale");
        if (type.equalsIgnoreCase("project"))
        this.setQueryParam("project_ids", id+"_project");
        this.setQueryParam("source", "android");
        this.setQueryParam("status", "active,inactive");
    }


    public JsonPath getPropertyDetail(String type, String id){
        addQueryParams(type, id);
        JsonPath response = new ApiExecutor(this)
                .execute()
                .validatableResponse()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath();
       return response;
    }



}

