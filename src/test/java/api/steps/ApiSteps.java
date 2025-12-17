package api.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class ApiSteps {

        String baseUrl;
        String appId = "63a804408eb0cb069b57e43a";
        String invalidAppId = "2323423mkk4223nj23";
        Response response;
        String createdUserId;

        @Given("the base URL is {string}")
        public void theBaseUrlIs(String url) {
            baseUrl = url;
        }

        @When("I send a POST request to {string} with the following data, firstName {string}, lastName {string}")
        public void sendPostRequestPositive(String endpoint, String firstName, String lastName) {

            JSONObject bodyObj = new JSONObject();

            bodyObj.put("firstName", firstName);
            bodyObj.put("lastName", lastName);

            String uniqueEmail = firstName.toLowerCase() + "." + lastName.toLowerCase() +
                System.currentTimeMillis() + "@example.com";
            bodyObj.put("email", uniqueEmail);

            response = RestAssured.given()
                .header("app-id", appId)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bodyObj.toString())
                .when()
                .post(baseUrl + endpoint);
        }

        @When("I send a boundary POST request to {string} with the following data, firstName {string}, lastName {string}")
        public void sendPostRequestBoundary(String endpoint, String firstName, String lastName) {

            JSONObject bodyObj = new JSONObject();

            bodyObj.put("firstName", firstName);
            bodyObj.put("lastName", lastName);

            String uniqueEmail = firstName.toLowerCase() + "." + lastName.toLowerCase() +
                    System.currentTimeMillis() + "@example.com";
            bodyObj.put("email", uniqueEmail);

            response = RestAssured.given()
                    .header("app-id", appId)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .body(bodyObj.toString())
                    .when()
                    .post(baseUrl + endpoint);
        }

        @When("I send a GET request to {string}")
            public void sendGetRequestInvalidUser(String endpoint) {

            response = RestAssured.given()
                    .header("app-id", appId)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .when()
                    .get(baseUrl + endpoint);

            if(response.getStatusCode() == 200) {
                response.jsonPath().getString("firstName");
                System.out.println("firstName: " + createdUserId);
            }
        }

        @When("I send am PUT request to {string} and update the firstName to {string}")
            public void updateUserData(String endpoint, String updatedFirstName) {

            JSONObject bodyObj = new JSONObject();

            bodyObj.put("firstName", updatedFirstName);

            response = RestAssured.given()
                    .header("app-id", appId)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .body(bodyObj.toString())
                    .when()
                    .put(baseUrl + endpoint);
        }

        @When("I send a GET request to {string} with an invalid appId")
            public void sendGetRequestWithInvalidAppid(String endpoint) {

            response = RestAssured.given()
                    .header("app-id", invalidAppId)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .when()
                    .get(baseUrl + endpoint);
        }

        @Then("the response status code should be {int}")
        public void theResponseStatusCodeShouldBe200(int statusCode) {
            assertThat(response.getStatusCode(), is(statusCode));
        }

        @And("the response body should contain id")
            public void responseBodyShouldContainId() {
                if(response.getStatusCode() == 200) {
                    createdUserId = response.jsonPath().getString("id");
                    System.out.println("Created userId: " + createdUserId);
                }
        }

        @And("the response firstName should be {string}")
            public void responseBodyShouldContainFirstname(String expectedName) {
                String actualName = response.jsonPath().getString("firstName");
                assert actualName.equals(expectedName) :
                    "Expected firstName: " + expectedName + ", but got: " + actualName;
            }
}
