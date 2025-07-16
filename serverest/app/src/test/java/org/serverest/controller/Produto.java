package org.serverest.controller;

import io.restassured.http.ContentType;
import org.serverest.model.ProdutoDTO;
import org.serverest.model.UsuarioDTO;
import org.serverest.util.Ambiente;
import org.serverest.util.Endpoint;
import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;
public class Produto {

    public static String cadastrar(ProdutoDTO produto, Integer statusCode, String mensagem, String ambiente, String authorization) {
        String produtoID = given()
                .body("{\n" +
                        "  \"nome\": \"" + produto.getNome() + "\",\n" +
                        "  \"preco\": \"" + produto.getPreco() + "\",\n" +
                        "  \"descricao\": \"" + produto.getDescricao() + "\",\n" +
                        "  \"quantidade\": \"" + produto.getQuantidade() + "\"\n" +
                        "}")
                .header("Authorization", authorization)
                .contentType(ContentType.JSON)
                .when()
                .post(ambiente.concat(Endpoint.produtos))
                .then()
                .statusCode(statusCode)
                .body("message", is(mensagem))
                .extract().path("_id");
        return produtoID;
    }

    public static void buscarPorId(String id, ProdutoDTO produto, Integer statusCode, String ambiente) {
        given()
                .pathParam("_id", id)
                .when()
                .get(ambiente.concat(Endpoint.produtosId))
                .then()
                .statusCode(statusCode)
                .body("nome", is(produto.getNome()))
                .body("preco", is(produto.getPreco()))
                .body("descricao", is(produto.getDescricao()))
                .body("quantidade", is(produto.getQuantidade()))
                .body("_id", is(produto.getId()));
    }
}
