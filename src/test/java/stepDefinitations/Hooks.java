package stepDefinitations;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@Deleteplace")
	public void beforeScenario() {
		//Write a code that will give you place_id
		//Execute this code only when place id is null
		StepDefinitations step = new StepDefinitations();
		if (StepDefinitations.place_id == null) {
		step.add_Place_Payload_with_and("Rajput", "English", "India");
		step.user_calls_with_http_request("addPlaceAPI", "POST");
		step.verify_place_Id_craeted_maps_to_using("Rajput", "getPlaceAPI");
		}
	}

}
