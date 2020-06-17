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
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitations extends Utils {
	
	RequestSpecification res;
	ResponseSpecification responseSpecification;
	Response response;
	TestDataBuild testData = new TestDataBuild();
	
	@Given("Add Place Payload with {string}{string} and {string}")
	public void add_Place_Payload_with_and(String name, String language, String address){
		res = given().spec(requestSpecification()).body(testData.addPlacePayLoad(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		//constructor will be called with value of resource
		APIResources resourceAPI = APIResources.valueOf(resource);
		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		response = res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
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
