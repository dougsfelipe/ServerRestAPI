package org.serverest.test;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.serverest.controller.Usuario;
import org.serverest.util.Ambiente;

public class TestExample {

    String ambiente = Ambiente.localhost;
    @Test
    public void listarUsuarios() {
        Usuario.listar(HttpStatus.SC_OK, ambiente);
    }
}
