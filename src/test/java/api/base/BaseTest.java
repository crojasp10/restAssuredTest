package api.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/v3/";
    }

    //Token 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
}

