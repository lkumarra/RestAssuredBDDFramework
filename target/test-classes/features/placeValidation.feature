Feature: Validating place API'S

Scenario: Verify if Place is being sucessfully added using AddPlaceAPI
		Given Add Place Payload
		When user calls "AddPlaceAPI" with Post http request
		Then the API call is success with status code 200
		And "status" in respose body is "OK"
		And "scope" in respose body is "APP"