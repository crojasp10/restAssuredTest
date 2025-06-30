package api.petStore;

import api.base.BaseTest;
import api.client.PetClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
            "id": 1.0,
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
    void deletePetShouldReturn200() {
        petClient.deletePet(8)
                .then()
                .statusCode(200);
    }

    @Test
    public void updatePetReturn200() {
        petClient.createPet(petObject).then().statusCode(200)
                .body("id", equalTo(10))
                .body("name", equalTo("doggie"))
                .body("status", equalTo("available"));
    }

    @Test
    public void getPetByIdReturns404() {
        petClient.getPetById(11)
                .then()
                .statusCode(404);
    }

    @Test
    public void createPetValidationsReturn200() {
        petClient.createPet(petObject).then().statusCode(200)
                .body("id", equalTo(10))
                .body("name", instanceOf(String.class))
                .body("name.length()", allOf(greaterThanOrEqualTo(3), lessThanOrEqualTo(15)))
                .body("category.id", instanceOf(Integer.class))
                .body("category.name", instanceOf(String.class))
                .body("photoUrls", not(empty()))
                .body("tags", hasSize(greaterThanOrEqualTo(1)))
                .body("tags[0].id", instanceOf(Integer.class))
                .body("tags[0].name", instanceOf(String.class))
                .body("status", equalTo("available"));

    }




}
