package org.serverest.factory;

import com.github.javafaker.Faker;
import org.serverest.model.CarrinhoDTO;
import org.serverest.model.ProdutoDTO;
import org.serverest.model.UsuarioDTO;

public class CarrinhoFactory {

    public static CarrinhoDTO criarCarrinho( UsuarioDTO usuarioDTO,ProdutoDTO produtoDTO, Integer quantidade) {
        CarrinhoDTO carrinhoDTO = new CarrinhoDTO(usuarioDTO.getId(), produtoDTO, quantidade);
        produtoDTO.setQuantidade(produtoDTO.getQuantidade()-quantidade);

        return carrinhoDTO;
    }
}
