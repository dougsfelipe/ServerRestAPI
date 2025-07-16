package org.serverest.test;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.serverest.controller.Carrinho;
import org.serverest.controller.Produto;
import org.serverest.controller.Usuario;
import org.serverest.factory.CarrinhoFactory;
import org.serverest.factory.ProdutoFactory;
import org.serverest.factory.UsuarioFactory;
import org.serverest.model.CarrinhoDTO;
import org.serverest.model.ProdutoDTO;
import org.serverest.model.UsuarioDTO;
import org.serverest.util.Ambiente;
import org.serverest.util.Mensagem;

public class Atividade {

    private String ambiente;

    @Before
    public void definirAmbiente() {
        ambiente = Ambiente.localhost;
    }


    @Test
    public void validarCarrinhoDiminui(){
        //Pre-Condicao
        UsuarioDTO usuarioDTO = UsuarioFactory.criarAdmin();

        // Cadastrar usuario com sucesso
        usuarioDTO.setId(Usuario.cadastrar(usuarioDTO, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente));

        Usuario.buscarPorId(usuarioDTO.getId(), usuarioDTO, HttpStatus.SC_OK, ambiente);

        //Logar Usuario
        usuarioDTO.setAuthorization(Usuario.logar(usuarioDTO, HttpStatus.SC_OK, Mensagem.loginSucesso, ambiente));

        //Criar Produto
        ProdutoDTO produtoDTO = ProdutoFactory.criarProduto(100,  10);
        produtoDTO.setId(Produto.cadastrar(produtoDTO, HttpStatus.SC_CREATED, Mensagem.produtoCadastrado, ambiente, usuarioDTO.getAuthorization()));

        //Verificar Protudo Criado
        Produto.buscarPorId(produtoDTO.getId(), produtoDTO, HttpStatus.SC_OK, ambiente);

        //Criar Carrinhp
        CarrinhoDTO carrinhoDTO = CarrinhoFactory.criarCarrinho(usuarioDTO, produtoDTO, 5);

        //Assosiar Carrinho
        carrinhoDTO.setId(Carrinho.cadastrar(produtoDTO.getId(), carrinhoDTO.getQuantidade(), usuarioDTO.getAuthorization(), HttpStatus.SC_CREATED, Mensagem.carrinhoCriado, ambiente));

        Produto.buscarPorId(produtoDTO.getId(), produtoDTO, HttpStatus.SC_OK, ambiente);


    }

    @Test
    public void validarCarrinhoCancelado(){
        //Pre-Condicao
        UsuarioDTO usuarioDTO = UsuarioFactory.criarAdmin();

        // Cadastrar usuario com sucesso
        usuarioDTO.setId(Usuario.cadastrar(usuarioDTO, HttpStatus.SC_CREATED, Mensagem.cadastroSucesso, ambiente));

        Usuario.buscarPorId(usuarioDTO.getId(), usuarioDTO, HttpStatus.SC_OK, ambiente);

        //Logar Usuario
        usuarioDTO.setAuthorization(Usuario.logar(usuarioDTO, HttpStatus.SC_OK, Mensagem.loginSucesso, ambiente));

        //Criar Produto
        ProdutoDTO produtoDTO = ProdutoFactory.criarProduto(100,  10);
        produtoDTO.setId(Produto.cadastrar(produtoDTO, HttpStatus.SC_CREATED, Mensagem.produtoCadastrado, ambiente, usuarioDTO.getAuthorization()));

        //Verificar Protudo Criado
        Produto.buscarPorId(produtoDTO.getId(), produtoDTO, HttpStatus.SC_OK, ambiente);

        //Criar Carrinhp
        CarrinhoDTO carrinhoDTO = CarrinhoFactory.criarCarrinho(usuarioDTO, produtoDTO, 5);

        //Assosiar Carrinho
        carrinhoDTO.setId(Carrinho.cadastrar(produtoDTO.getId(), carrinhoDTO.getQuantidade(), usuarioDTO.getAuthorization(), HttpStatus.SC_CREATED, Mensagem.carrinhoCriado, ambiente));
        Produto.buscarPorId(produtoDTO.getId(), produtoDTO, HttpStatus.SC_OK, ambiente);

        Carrinho.cancelarCompra(usuarioDTO, carrinhoDTO, produtoDTO, HttpStatus.SC_OK, Mensagem.compraCancelada, ambiente);

        Produto.buscarPorId(produtoDTO.getId(), produtoDTO, HttpStatus.SC_OK, ambiente);



    }
}
