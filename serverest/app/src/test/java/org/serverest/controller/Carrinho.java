package org.serverest.controller;

import io.restassured.http.ContentType;
import org.serverest.model.CarrinhoDTO;
import org.serverest.model.ProdutoDTO;
import org.serverest.model.UsuarioDTO;
import org.serverest.util.Ambiente;
import org.serverest.util.Endpoint;
import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Carrinho {
    public static String cadastrar(String produtoId, Integer quantidade, String usuarioToken, Integer statusCode, String mensagem, String ambiente) {
        String ID = given()
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
                .body("message", is(mensagem))
                .extract().path("_id");

        return ID;
    }

    public static void buscarPorId(String id, CarrinhoDTO carrinho, Integer statusCode, String ambiente) {
        given()
                .pathParam("_id", id)
                .when()
                .get(ambiente.concat(Endpoint.carrinhosId))
                .then()
                .statusCode(statusCode)
                .body("precoTotal", is(carrinho.getPrecoTotal()))
                .body("quantidadeTotal", is(carrinho.getQuantidade()))
                .body("idUsuario", is(carrinho.getUsuarioId()))
                .body("_id", is(carrinho.getId()))
                .body("produtos[0].idProduto", equalTo(carrinho.getProduto().getId()))
                .body("produtos[0].quantidade", equalTo(carrinho.getQuantidade()))
                .body("produtos[0].precoUnitario", equalTo(carrinho.getProduto().getPreco()));
    }

    public static void buscarPorIdExcluido(String id, Integer statusCode, String mensagem, String ambiente) {
        given()
                .pathParam("_id", id)
                .when()
                .get(ambiente.concat(Endpoint.carrinhosId))
                .then()
                .statusCode(statusCode)
                .body("message", is(mensagem));
    }

    public static void concluirCompra(UsuarioDTO usuario, CarrinhoDTO carrinho, ProdutoDTO produto, Integer statusCode, String mensagem, String ambiente) {
        given()
                .header("authorization", usuario.getAuthorization())
                .when()
                .delete(ambiente.concat(Endpoint.concluirCompra))
                .then()
                .statusCode(statusCode)
                .body("message", is(mensagem));


    }

    public static void cancelarCompra(UsuarioDTO usuario, CarrinhoDTO carrinho, ProdutoDTO produto, Integer statusCode, String mensagem, String ambiente) {
        given()
                .header("authorization", usuario.getAuthorization())
                .when()
                .delete(ambiente.concat(Endpoint.cancelarCompra))
                .then()
                .statusCode(statusCode)
                .body("message", is(mensagem));

        produto.setQuantidade(produto.getQuantidade() + carrinho.getQuantidade());


    }
}