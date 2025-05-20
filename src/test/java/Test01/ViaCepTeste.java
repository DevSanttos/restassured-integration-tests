package Test01;

import br.com.jonathan.config.BaseTeste;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;


public class ViaCepTeste extends BaseTeste {

    @Test
    public void deveRetornarDadosCorretosParaCepValido() {
        given()
            .when()
                .get("/ws/35540000/json/")
            .then()
                .statusCode(200) //status http
                .body("localidade", equalTo("Oliveira")) //validação
                .log().all(); //corpo da resposta no terminal
    }

    @Test
    public void deveRetornarDadosParaCepSemHifen() {
        given().pathParam("cep", "01001000")
                .when().get("/ws/{cep}/json/")
                .then()
                .statusCode(200)
                .body("cep", equalTo("01001-000"))
                .log().all();
    }

    @Test
    public void deveRetornarDadosParaCepComHifen() {
        given().pathParam("cep", "01001-000")
                .when().get("/ws/{cep}/json/")
                .then()
                .statusCode(200)
                .body("cep", equalTo("01001-000"))
                .log().all();
    }

    @Test
    public void deveRetornarEmTempoAceitavel() {
        given().pathParam("cep", "01001000")
                .when().get("/ws/{cep}/json/")
                .then().time(lessThan(3000L)); // Tempo em milissegundos (2 segundos, L de Long)
    }

    @Test
    public void deveRetornar200ComCepValido() {
        given(). //é como o Arrange, onde a gente prepara tudo
                baseUri("https://viacep.com.br"). //URL da API
                when(). //é como o Act, quando executamos a requisição
                get("/ws/89140000/json/"). // CEP
                then(). //é como o Assert, onde validamos se a resposta veio como esperado
                statusCode(200);
    }

    @Test
    public void deveValidarRespostaParaCepValido() {
        RestAssured.baseURI = "https://viacep.com.br"; //URL da API
        given(). //é como o Arrange, onde a gente prepara tudo
                basePath("/ws/89140000/json/"). //Caminho
                when(). //é como o Act, quando executamos a requisição
                get().
                then(). //é como o Assert, onde validamos se a resposta veio como esperado
                statusCode(200).
                body("localidade", equalTo("Ibirama")).
                body("uf", equalTo("SC"))
                .log().all();
    }
}