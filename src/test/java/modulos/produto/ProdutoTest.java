package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach() {
        //Configurando os dados da API Rest da Lojinha
        baseURI = "http://165.227.93.41";
        basePath = "/lojinha";

        //Obter o token do usuario admin
        this.token = given() //no given é necessario colocar o header e o body
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.fazerLoginComUsuario("admin", "admin"))
            .when() //diz qual o método que quero usar
                .post("/v2/login")
            .then() //"então" o que queremos que aconteça dps de enviar a requisição
                .extract()
                    .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor do Produto igual 0.00 não é permitido")
    public void testValidarLimitesZeradoProibidoValorProduto() {
        //Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada
        //Também deve ser apresentado o status code 422
        //INICIO DO TESTE
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(0.00))
        .when()
                .post("/v2/produtos")
        .then()
                .assertThat() //valide que
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }

    @Test
    @DisplayName("Validar que o valor do Produto igual 7000.01 não é permitido")
    public void testValidarLimitesMaiorSeteMilProibidoValorProduto() {
        //Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada
        //Também deve ser apresentado o status code 422
        //INICIO DO TESTE
        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(7000.01))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat() //valide que
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }
}
