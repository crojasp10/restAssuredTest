package api.petStore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetApiTest extends BaseTest{

    @Test
    public void getPetById_Returns200() {

        Response response = RestAssured
                .given()
                .when()
                .get("/3")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String contentType = response.getHeader("Content-Type");
        assertThat(contentType, containsString("application/json"));

        int petId = response.jsonPath().getInt("id");
        assertThat(petId, equalTo(4));
    }

}
