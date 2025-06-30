package api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetClient {

    public Response getPetById(int id) {
        return given()
                .when()
                .get("/pet/" + id);
    }

    public Response createPet(Object pet) {
        return given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("/pet");
    }

    public Response deletePet(int id) {
        return given()
                .when()
                .delete("/pet/" + id);
    }

    public Response updatePet(Object pet) {
        return given()
                .header("Content-Type", "application/json")
                .body(pet)
                .when()
                .put("/pet");
    }

}
