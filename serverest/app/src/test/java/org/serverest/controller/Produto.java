package org.serverest.controller;

import io.restassured.http.ContentType;
import org.serverest.model.ProdutoDTO;
import org.serverest.util.Ambiente;
import org.serverest.util.Endpoint;
import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;
public class Produto {
    public static String cadastrar(ProdutoDTO produtoDTO, String usuarioToken, Integer statusCode, String mensagem) {

        return given()
                .header("Authorization", usuarioToken)
                .body("{\n" +
                        "  \"nome\": \"" + produtoDTO.getNome() + "\",\n" +
                        "  \"preco\": " + produtoDTO.getPreco() + ",\n" +
                        "  \"descricao\": \"" + produtoDTO.getDescricao() + "\",\n" +
                        "  \"quantidade\": " + produtoDTO.getQuantidade() + "\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post(Ambiente.localhost + Endpoint.produtos)
                .then()
                .statusCode(statusCode)
                .body("message", is(mensagem))
                .extract().path("_id");
    }

    public static void verificarEstoque(String id, Integer quantidadeEsperada, Integer statusCode) {

        given()
                .pathParam("_id", id)
                .when()
                .get(Ambiente.localhost + Endpoint.produtosId)
                .then()
                .body("quantidade", is(quantidadeEsperada));
    }
}
