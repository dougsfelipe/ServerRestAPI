package org.serverest.test;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.serverest.controller.Usuario;
import org.serverest.factory.UsuarioFactory;
import org.serverest.model.UsuarioDTO;
import org.serverest.util.Ambiente;
import org.serverest.util.Mensagem;

public class Exercicio01 {

    private String ambiente;

    @Before
    public void definirAmbiente() {
        ambiente = Ambiente.localhost;
    }

    @Test
    public void validarCadastro() {

        //Pre-condicaoo
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO = UsuarioFactory.criarAdmin();

        //Validar cadastro com sucesso
        usuarioDTO.setId(Usuario.cadastrar(usuarioDTO, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente));

        //Validar cadastro com email duplicado
        Usuario.cadastrar(usuarioDTO, HttpStatus.SC_BAD_REQUEST, Mensagem.emailUtilizado, ambiente);
    }

    @Test
    public void validarExclusao() {

        //Pre-condicao
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO = UsuarioFactory.criarAdmin();
        usuarioDTO.setId(Usuario.cadastrar(usuarioDTO, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente));

        //Validar exclusao de usuario existente
        Usuario.excluir(usuarioDTO.getId(), HttpStatus.SC_OK, Mensagem.excluidoSucesso, ambiente);

        //Validar exclusao de usuario inexistente
        Usuario.excluir(usuarioDTO.getId(), HttpStatus.SC_OK, Mensagem.nenhumRegistroExcluido, ambiente);
    }

    @Test
    public void validarEdicao() {

        //Pre-condicao
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        UsuarioDTO usuario2DTO = new UsuarioDTO();
        usuarioDTO = UsuarioFactory.criarAdmin();
        usuario2DTO = UsuarioFactory.criarAdmin();
        usuarioDTO.setId(Usuario.cadastrar(usuarioDTO, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente));

        //Validar edicao de usuario existente
        Usuario.editar(usuarioDTO.getId(), usuarioDTO, HttpStatus.SC_OK, Mensagem.alteradoSucesso, ambiente);

        //Validar edicao de usuario inexistente
        Usuario.editar("jogfODIlXsqxNFS5", usuario2DTO, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente);

        //Validar edicao de usuario com email duplicado
        usuarioDTO.setEmail(usuario2DTO.getEmail());
        Usuario.editar(usuarioDTO.getId(), usuarioDTO, HttpStatus.SC_BAD_REQUEST, Mensagem.emailUtilizado, ambiente);
    }

    @Test
    public void validarBuscarPorId() {

        //Pre-condicao
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO = UsuarioFactory.criarAdmin();
        usuarioDTO.setId(Usuario.cadastrar(usuarioDTO, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente));

        //Validar busca de usuario por id
        Usuario.buscarPorId(usuarioDTO.getId(), usuarioDTO, HttpStatus.SC_OK, ambiente);
    }

    @Test
    public void validarListagem() {

        //Validar listagem de usuarios
        Usuario.listar(HttpStatus.SC_OK, ambiente);
    }
}
