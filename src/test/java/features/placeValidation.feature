Feature: Validating place API'S

  @Addplace
  Scenario Outline: Verify if Place is being sucessfully added using AddPlaceAPI
    Given Add Place Payload with "<Name>""<Language>" and "<Address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in respose body is "OK"
    And "scope" in respose body is "APP"
    And verify place_Id craeted maps to "<Name>" using "getPlaceAPI"

    Examples: 
      | Name    | Language | Address            |
      | AAhouse | English  | World Cross Center |
      | BBhouse | Spanish  | Sea Cross Center   |

  @Deleteplace
  Scenario: Verify delete functionality working fine
    Given Delete place payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then Api call got success with status code 200
    And "status" in responce body is "OK"
