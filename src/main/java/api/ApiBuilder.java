package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiBuilder {

    private static final Logger logger = LogManager.getLogger(ApiBuilder.class);

    private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

    private ApiBuilder.MethodType method;

    public ApiBuilder.MethodType getMethod() {
        return this.method;
    }

    public void setMethod(ApiBuilder.MethodType method) {
        this.method = method;
    }

    public RequestSpecBuilder getRequestSpecBuilder() {
        return this.requestSpecBuilder;
    }

    public Response execute() {
        RequestSpecification requestSpecification = this.requestSpecBuilder.build();
        RestAssured.defaultParser = Parser.JSON;
        RestAssuredConfig config = RestAssured.config();
        Response response;
        switch (this.method) {
            case GET:
                response = (Response) RestAssured.given().config(config).spec(requestSpecification).when().get();
                break;
            case POST:
                response = (Response) RestAssured.given().config(config).spec(requestSpecification).when().post();
                break;
            case PUT:
                response = (Response) RestAssured.given().config(config).spec(requestSpecification).when().put();
                break;
            case DELETE:
                response = (Response) RestAssured.given().config(config).spec(requestSpecification).when().delete();
                break;
            case PATCH:
                response = (Response) RestAssured.given().config(config).spec(requestSpecification).when().patch();
                break;
            default:
                throw new RuntimeException("API method not specified");
        }

//        this.printResponse(response);
        return response;
    }

    // Not in use and response is getting printed through ApiLoggingFilter
    private void printResponse(Response response) {
        String contentType = response.contentType();
        if (!contentType.toLowerCase().contains("text/html") && !contentType.toLowerCase().contains("text/plain")) {
            logger.info("API RESPONSE: " + response.getBody().prettyPrint());
        } else {
            DateFormat timeFormat = new SimpleDateFormat("MM.dd.yyyy HH-mm-ss");
            String fileName = org.testng.Reporter.getCurrentTestResult().getMethod().getMethodName() + "_" + timeFormat.format(new Date()) + ".html";
            String outputDir = org.testng.Reporter.getCurrentTestResult().getTestContext().getOutputDirectory();
            outputDir = outputDir.substring(0, outputDir.lastIndexOf(File.separator)) + "/html";
            File file = new File(outputDir + File.separator + fileName);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            PrintWriter writer = null;

            try {
                file.createNewFile();
                writer = new PrintWriter(file);
                writer.write(response.asString());
                writer.flush();
//                this.report.attachHtml(file);
            } catch (Throwable var12) {
                throw new RuntimeException(var12);
            } finally {
                writer.close();
            }
        }

    }


    public static enum MethodType {
        POST,
        GET,
        PUT,
        DELETE,
        PATCH;

        private MethodType() {
        }
    }

}

