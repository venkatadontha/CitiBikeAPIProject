package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Utilities;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MyStepdefs extends Utilities {
    RequestSpecification request;
    Response response;
    int responseStatusCode;

    @Given("endpoints with no auth and no headers")
    public void endpoints_with_no_auth_and_no_headers() {
        System.out.println("given code");
        throw new io.cucumber.java.PendingException();
    }

    @When("user triggers GET API request call")
    public void GET_APIRequest() throws IOException 
    {
    	System.out.println("\n**********Step-1 : Get Request for the API Endpoint");
    	response = given()
        		 .when()
        		 .get(getURI("baseUrl"))
        		 .then()
                 .extract()
                 .response();
        System.out.println("\n");
    }


    @Then("Status code should be {int}")
    public void fetchStatusCode(int statusCode) 
    {
    	int responseStatusCode = response.getStatusCode();
        System.out.println("\n**********Step-2 : Status Code for the GET API request is: " + responseStatusCode);
        System.out.println("\n");
        assertEquals("\n Test Failed because " + responseStatusCode + " does not match with Expected", statusCode,responseStatusCode);
    }

    @Then("user should get the total Networks Count {int} in Response body")
    public void fetchNetworkCount(int count) 
    {
        System.out.println("\n**********Step-3 : Network Count Validation Test");
        System.out.println("\n");
        int NetworksCount = (getCount(response, "networks.size()"));
        System.out.println("---------->The total number of Networks Count is : " + NetworksCount );
        System.out.println("\n");
        assertEquals("\n Test Failed because " + responseStatusCode + " does not match with Expected", count, NetworksCount);

    }

    @Then("^user should get the city \"([^\"]*)\" and \"([^\"]*)\"$")
    public void fetchCityAndCountry(String validateCity, String validateCountry) 
    {
        System.out.println("\n**********Step-4 : Test to validate that the city and country");
        System.out.println("\n");
        boolean flag =  getCity(response,"networks.size()", validateCity, validateCountry);
        assertTrue("Test Failed because the city Frankfurt is not in the country DE", flag);
    }

    @Then("^user should get (.+) and (.+) corresponding to city \"([^\"]*)\"$")
    public void fetchLatitudeAndLongitude(double latitude, double longitude,  String validateCity) 
    {
        System.out.println("\n**********Step-5 :Longitude and Latitude for " + validateCity + " is : ");
        System.out.println("\n");
        HashMap<String, String> frankfurtTest;
        frankfurtTest = getCityData(response, "networks.size()", validateCity);
        System.out.println("---------->Latitude for the city Frankfurt : " + frankfurtTest.get("latitude"));
        System.out.println("---------->Longitude for the city Frankfurt is : " + frankfurtTest.get("longitude"));
        System.out.println("\n");
        if(frankfurtTest.get("country").equalsIgnoreCase(validateCity))
        {
            System.out.println(frankfurtTest.get("city") +" is in Country " +frankfurtTest.get("country"));
        }


    }

}
