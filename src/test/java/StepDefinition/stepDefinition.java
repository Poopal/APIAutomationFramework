package StepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resourses.APIResourses;
import resourses.TestDataBuild;
import resourses.Utils;

public class stepDefinition extends Utils {

	RequestSpecification resq;
	ResponseSpecification respSpec;
	Response response;
	static String placeId;

	TestDataBuild td = new TestDataBuild();

	@Given("Add place payload with {string}  {string}  {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		resq = given().spec(requestSpecification()).body(td.addPlace(name, language, address));
	}

	@When("user calls {string} with {string} http Request")
	public void user_calls_with_http_Request(String resourse, String method) {
		APIResourses rAPI = APIResourses.valueOf(resourse);
		System.out.println(rAPI.getResourse());

		respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST")) {
			response = resq.when().post(rAPI.getResourse());
		} else if (method.equalsIgnoreCase("GET")) {
			response = resq.when().post(rAPI.getResourse());
		}
		response = resq.when().post(rAPI.getResourse()).then().spec(respSpec).extract().response();
	}

	@Then("the API Call is success with Status Code {int}")
	public void the_API_Call_is_success_with_Status_Code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String actualKey, String Expectedvalue) {
		assertEquals(getJsonPath(response, actualKey), Expectedvalue);
	}

	@Then("Verify place_id created maps to {string} using  {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resourse) throws IOException {

		placeId = getJsonPath(response, "place_id");
		resq = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_Request(resourse, "GET");
		
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName,expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
	    
		resq= given().spec(requestSpecification()).body(Utils.deletePlacePayload(placeId));
	}

}
