package api.petStore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetApiTest extends BaseTest{

    @Test
    public void getPetByIdReturns200() {

        Response response = given()
                .when()
                .get("/3")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String contentType = response.getHeader("Content-Type");
        assertThat(contentType, containsString("application/json"));

        int petId = response.jsonPath().getInt("id");
        assertThat(petId, equalTo(3));
    }

    @Test
    public void createPetWithFullJson_ShouldReturn200() {
        String requestBody = """
        {
          "id": 10,
          "name": "doggie",
          "category": {
            "id": 1,
            "name": "Dogs"
          },
          "photoUrls": [
            "string"
          ],
          "tags": [
            {"id": 1,
             "name": "tag2"
             },
             {
              "id": 2,
              "name": "tag3"
             }
          ],
          "status": "available"
        }
        """;

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("id", equalTo(10))
                .body("name", equalTo("doggie"))
                .body("status", equalTo("available"));
    }







}
