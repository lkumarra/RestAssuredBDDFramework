package stepDefinitations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

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
	static String place_id;
	TestDataBuild testData = new TestDataBuild();
	
	@Given("Add Place Payload with {string}{string} and {string}")
	public void add_Place_Payload_with_and(String name, String language, String address){
		res = given().spec(requestSpecification()).body(testData.addPlacePayLoad(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		responseSpecification = new ResponseSpecBuilder().build();
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
	   assertEquals(getJsonPath(response, keyValue), Expectedvalue);
	}
	
	@Then("verify place_Id craeted maps to {string} using {string}")
	public void verify_place_Id_craeted_maps_to_using(String Name, String resource) {
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		assertEquals(getJsonPath(response, "name"), Name);
	}
	
	@Given("Delete place payload")
	public void delete_place_payload() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("place_id",place_id);
		System.out.println(place_id);
	    res= given().spec(requestSpecification()).body(map).contentType(ContentType.JSON);
	}
	
	@Then("Api call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1) {
	   assertEquals(response.statusCode(), 200);
	}

	@Then("{string} in responce body is {string}")
	public void in_responce_body_is(String jsonKey, String value) {
	assertEquals(getJsonPath(response, jsonKey), value);
	}

}
