package org.serverest.factory;

import com.github.javafaker.Faker;
import org.serverest.model.ProdutoDTO;

public class ProdutoFactory {

    public static ProdutoDTO criarLivro(Integer preco, Integer quantidadeInicial) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        Faker faker = new Faker();
        String nome = faker.book().title();
        produtoDTO.setNome(nome);
        produtoDTO.setPreco(preco);
        produtoDTO.setDescricao("Livro");
        produtoDTO.setQuantidade(quantidadeInicial);
        return produtoDTO;
    }

    public static ProdutoDTO criarProduto(Integer preco, Integer quantidadeInicial) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        Faker faker = new Faker();
        String nome = faker.commerce().productName();
        produtoDTO.setNome(nome);
        produtoDTO.setPreco(preco);
        produtoDTO.setDescricao("Produto");
        produtoDTO.setQuantidade(quantidadeInicial);
        return produtoDTO;
    }
}