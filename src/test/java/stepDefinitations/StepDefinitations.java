package stepDefinitations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitations extends Utils {
	
	RequestSpecification res;
	ResponseSpecification responseSpecification;
	Response response;
	TestDataBuild testData = new TestDataBuild();
	
	@Given("Add Place Payload")
	public void add_Place_Payload() {
		res = given().spec(requestSpecification()).body(testData.addPlacePayLoad());
	}

	@When("user calls {string} with Post http request")
	public void user_calls_with_Post_http_request(String string) {
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = res.when().post("maps/api/place/add/json").then().spec(responseSpecification).extract().response();
	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
	  assertEquals( response.getStatusCode(),200);
	}

	@Then("{string} in respose body is {string}")
	public void in_respose_body_is(String keyValue, String Expectedvalue) {
	   String resp = response.asString();
	   JsonPath json = new JsonPath(resp);
	   String status = json.get(keyValue);
	   assertEquals(status, Expectedvalue);
	}

}
