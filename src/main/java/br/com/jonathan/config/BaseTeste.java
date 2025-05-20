package br.com.jonathan.config;

import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;


public class BaseTeste {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://viacep.com.br";
    }
}
