package api.petStore;

import api.base.BaseTest;
import api.client.PetClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetApiTest extends BaseTest {

    private final PetClient petClient = new PetClient();
    String petObject = "";

    @BeforeEach
    void setupPet() {
        petObject = """
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
    }

    @Test
    public void getPetByIdReturns200() {
            petClient.getPetById(3)
                    .then()
                    .statusCode(200)
                    .body("id", equalTo(3));
    }

    @Test
    public void createPetReturn200() {
        petClient.createPet(petObject).then().statusCode(200)
                .body("id", equalTo(10))
                .body("name", equalTo("doggie"))
                .body("status", equalTo("available"));

    }

    @Test
    void deletePet_ShouldReturn200() {
        petClient.deletePet(8)
                .then()
                .statusCode(200);
    }



}
