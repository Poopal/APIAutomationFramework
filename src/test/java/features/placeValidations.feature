Feature: Validating Place API

Scenario Outline: Verify if place is being added sucessfully using AddPlaceAPI
        Given Add place payload with "<name>"  "<language>"  "<address>"
        When user calls "AddPlaceAPI" with "POST" http Request 
        Then the API Call is success with Status Code 200
        And "status" in response body is "OK"
        And "scope" in response body is "APP"
        And Verify place_id created maps to "<name>" using  "GetPlaceAPI"

Examples: 

			|  name       | launguage     | address                |
			|  Irfan khan | Hindi         | World Trade Center     |
#			|  Sreedevi   | English		  | Rushmore Mountains     |
			
		
Scenario: Verify if delete Place functionality is working

Given DeletePlace Payload
When user calls "DeletePlaceAPI" with "POST" http Request
Then the API Call is success with Status Code 200
And "status" in response body is "OK"


			
			