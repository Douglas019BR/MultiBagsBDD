package unicamp.br.inf321;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.not;


public class ProductReviewStepDefinitions {
    String ProductReviewUrl = "/api/v1/auth/products/{id}/reviews";
    private final CucumberWorld cucumberWorld;

    public ProductReviewStepDefinitions(CucumberWorld cucumberWorld) {
        this.cucumberWorld = cucumberWorld;
    }

    @When("he selects the option to create a review from product {string}")
    public void heSelectsTheOptionToCreateProductReviews(String productId, Map<String, String> table) {
        String requestUrl = ProductReviewUrl.replace("{id}", productId);
        String token = cucumberWorld.getFromNotes("token");
        int customerId = cucumberWorld.getFromNotes("customerId");
        Map<String, Object> body = Map.of(
            "customerId", customerId,
            "description", table.get("description"),
            "language", table.get("language"),
            "rating", table.get("rating")
        );
        cucumberWorld.setResponse(cucumberWorld.getRequest()
                .when().header("Authorization", "Bearer " + token)
                .body(body)
                .post(requestUrl));
    }

    @When("he selects the option to update a review from product {string}")
    public void heSelectsTheOptionToUpdateProductReviews(String productId, Map<String, String> table) {
        int reviewId = cucumberWorld.getFromNotes("reviewId");
        String requestUrl = ProductReviewUrl.replace("{id}", productId) + "/" + reviewId;
        String token = cucumberWorld.getFromNotes("token");
        Map<String, Object> body = Map.of(
                "customerId", cucumberWorld.getFromNotes("customerId"),
                "description", table.get("description"),
                "language", table.get("language"),
                "rating", table.get("rating")
        );
        cucumberWorld.setResponse(cucumberWorld.getRequest()
                .when().header("Authorization", "Bearer " + token)
                        .body(body)
                .put(requestUrl));
    }

    @Then("the product review should be created with success")
    public void shouldBeCreateReviewProductsWithSuccess() {
        cucumberWorld.getResponse().then().log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .body(matchesJsonSchemaInClasspath("unicamp/br/inf321/ProductReviewJsonSchema.json"));
    }

    @When("he selects the option to delete a review from product {string}")
    public void heSelectsTheOptionToDeleteProductReviews(String productId) {
        int reviewId = cucumberWorld.getFromNotes("reviewId");
        String requestUrl = ProductReviewUrl.replace("{id}", productId) + "/" + reviewId;
        String token = cucumberWorld.getFromNotes("token");
        cucumberWorld.setResponse(cucumberWorld.getRequest()
                .when().header("Authorization", "Bearer " + token)
                .delete(requestUrl));
    }

    @When("get the reviewId")
    public void getReviewId() {
        int customerId = cucumberWorld.getFromNotes("customerId");
        JsonPath response = cucumberWorld.getResponse().jsonPath();
        for (Map<String, Object> review : response.getList("$", Map.class)) {
            Map<String, Object> customer = (Map<String, Object>) review.get("customer");
            if (customerId == (int) customer.get("id")) {
                int reviewId = (int) review.get("id");
                cucumberWorld.addToNotes("reviewId", reviewId);
                break;
            }
        }
    }

    @Then("the product review should be deleted with success")
    public void shouldBeDeleteReviewProductsWithSuccess() {
        cucumberWorld.getResponse().then().log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Then("the product review should be updated with success")
    public void shouldBeUpdateReviewProductsWithSuccess() {
        cucumberWorld.getResponse().then().log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("unicamp/br/inf321/ProductReviewJsonSchema.json"));
    }
}