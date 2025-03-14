package unicamp.br.inf321;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ListProductReviewsStepDefinitions {
    String ListProductReviewsUrl = "/api/v1/products/{id}/reviews";
    private final CucumberWorld cucumberWorld;

    public ListProductReviewsStepDefinitions(CucumberWorld cucumberWorld) {
        this.cucumberWorld = cucumberWorld;
    }


    @When("he selects the option to see reviews from product {string}")
    public void heSelectsTheOptionToSeeProductReviews(String productId) {
        String requestUrl = ListProductReviewsUrl.replace("{id}", productId);
        String token = cucumberWorld.getFromNotes("token");
        cucumberWorld.setResponse(cucumberWorld.getRequest()
                .when().header("Authorization", "Bearer " + token)
                .get(requestUrl));
    }

    @Then("the product reviews should be shown with success")
    public void shouldBeListReviewProductsWithSuccess() {
        cucumberWorld.getResponse().then().log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("unicamp/br/inf321/ListProdutReviewsJsonSchema.json"));
    }

    @Then("the product reviews should not be shown with success")
    public void shouldBeListReviewProductsWithoutSuccess() {
        cucumberWorld.getResponse().then().log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(matchesJsonSchemaInClasspath("unicamp/br/inf321/ErrorJsonSchema.json"))
                .body("error", is(equalTo("Not Found")))
                .body("message", is(equalTo("Product id 666 does not exists")));
    }
}
