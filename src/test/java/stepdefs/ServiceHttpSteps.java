package stepdefs;

import com.qabound.spring.model.Quote;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.time.Instant;

public class ServiceHttpSteps {

    private final RequestSpecification restAssuredSpec;
    private String restSvcUrl = "http://localhost:8080";
    private String getAllEndpoint = "api/v1/quotes";
    private Response response;

    public ServiceHttpSteps() {
        this.restAssuredSpec = RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter()).given();
    }

    @When("A request GET all request is sent to the service")
    public void sentGetAllRequest() {
        this.response = RestAssured.given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .given().baseUri(restSvcUrl).get(getAllEndpoint);
    }

    @When("A post request is sent to the service")
    public void sentAddQuoteRequest() {
        var quote = Quote.builder()
                .quote("I have the high ground")
                .date(Instant.now().toEpochMilli())
                .source("Star Wars: Episode III -- Revenge of the Sith")
                .build();

        this.response = RestAssured
                .given()
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                .contentType(ContentType.JSON)
                .baseUri(restSvcUrl)
                .body(quote)
                .post(getAllEndpoint);
    }

    @Then("A {int} error should be returned")
    public void aErrorShouldBeReturned(int expectedStatusCode) {
        int respStatusCode = response.andReturn().statusCode();
        Assert.assertEquals(expectedStatusCode, respStatusCode);
    }
}
