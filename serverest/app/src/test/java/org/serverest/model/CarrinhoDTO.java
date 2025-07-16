package org.serverest.model;

public class CarrinhoDTO {

    private String id;
    private String usuarioId;
    private ProdutoDTO produto;
    private Integer quantidade;
    private Integer precoTotal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Integer precoTotal) {
        this.precoTotal = precoTotal;
    }
    public CarrinhoDTO(String usuarioId, ProdutoDTO produto, Integer quantidade) {
        this.usuarioId = usuarioId;
        this.quantidade = quantidade;
        this.produto = produto;
        this.precoTotal = this.quantidade*produto.getPreco();
    }


}