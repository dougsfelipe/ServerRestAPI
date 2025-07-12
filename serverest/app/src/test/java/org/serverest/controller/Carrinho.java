package org.serverest.controller;

import io.restassured.http.ContentType;
import org.serverest.util.Ambiente;
import org.serverest.util.Endpoint;
import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;
public class Carrinho {
    public static void cadastrar(String produtoId, Integer quantidade, String usuarioToken, Integer statusCode, String mensagem, String ambiente) {
        given()
                .header("authorization", usuarioToken)
                .body("{\n" +
                        "  \"produtos\": [\n" +
                        "    {\n" +
                        "      \"idProduto\": \"" + produtoId + "\",\n" +
                        "      \"quantidade\": " + quantidade + "\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .contentType(ContentType.JSON)
        .when()
                .post(ambiente.concat(Endpoint.carrinhos))
        .then()
                .statusCode(statusCode)
                .body("message", is(mensagem));
    }

    public static void cancelarCompra(String usuarioToken, Integer statusCode, String mensagem, String ambiente) {
        given()
                .header("authorization", usuarioToken)
        .when()
                .delete(ambiente.concat(Endpoint.cancelarCompra))
        .then()
                .statusCode(statusCode)
                .body("message", is(mensagem));
    }
}