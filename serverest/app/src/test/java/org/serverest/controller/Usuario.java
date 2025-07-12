package org.serverest.controller;

import org.serverest.model.UsuarioDTO;
import io.restassured.http.ContentType;
import org.serverest.util.Ambiente;
import org.serverest.util.Endpoint;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static io.restassured.RestAssured.given;
public class Usuario {
    public static String cadastrar(UsuarioDTO usuarioDTO, Integer statusCode, String mensagem, String ambiente) {

        return given()
                .body("{\n" +
                        "  \"nome\": \"" + usuarioDTO.getNome() + "\",\n" +
                        "  \"email\": \"" + usuarioDTO.getEmail() + "\",\n" +
                        "  \"password\": \"" + usuarioDTO.getPassword() + "\",\n" +
                        "  \"administrador\": \"" + usuarioDTO.getAdministrador() + "\"\n" +
                        "}")
                .contentType(ContentType.JSON)
            .when()
                .post(ambiente.concat(Endpoint.usuarios))
            .then()
                .statusCode(statusCode)
                .body("message", is(mensagem))
                .extract().path("_id");
    }

    public static String autenticar(UsuarioDTO usuarioDTO, Integer statusCode, String message, String ambiente) {

        return given()
                .body("{\n" +
                        "  \"email\": \"" + usuarioDTO.getEmail() + "\",\n" +
                        "  \"password\": \"" + usuarioDTO.getPassword() + "\"\n" +
                        "}")
                .contentType(ContentType.JSON)
            .when()
                .post(ambiente.concat(Endpoint.login))
            .then()
                .statusCode(statusCode)
                .body("message", is(message))
                .extract().path("authorization");
    }

    public static void listar(Integer statusCode, String ambiente) {

        when()
                .get(ambiente.concat(Endpoint.usuarios))
        .then()
                .statusCode(statusCode);
    }
}