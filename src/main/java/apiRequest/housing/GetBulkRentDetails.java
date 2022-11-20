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

public class GetBulkRentDetails extends ApiRequest {
    private static final Logger logger = LogManager.getLogger(GetBulkBuyDetails.class);


    public GetBulkRentDetails() {
        this.baseUrl = HousingConfig.readHousingProperties().get("khojService");
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
        return "/api/v0/rent/get_bulk_rent_details";
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
        this.setQueryParam("rent_ids", id);
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
