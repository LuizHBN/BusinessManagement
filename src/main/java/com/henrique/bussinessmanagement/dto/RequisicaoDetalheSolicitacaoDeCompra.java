package com.henrique.bussinessmanagement.dto;

import com.henrique.bussinessmanagement.model.CentroDeCusto;
import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.SolicitacaoDeCompra;

import java.math.BigDecimal;

public class RequisicaoDetalheSolicitacaoDeCompra {
    private SolicitacaoDeCompra solicitacaoDeCompra;
    private Produto produto;
    private CentroDeCusto centroDeCusto;
    private BigDecimal quantidade;
    private BigDecimal valor;

    public DetalheSolicitacaoDeCompra toDetalheSolicitacaoDeCompra(SolicitacaoDeCompra solicitacaoDeCompra){
        DetalheSolicitacaoDeCompra detalheSolicitacaoDeCompra = new DetalheSolicitacaoDeCompra();

        detalheSolicitacaoDeCompra.setSolicitacaoDeCompra(solicitacaoDeCompra);
        detalheSolicitacaoDeCompra.setProduto(produto);
        detalheSolicitacaoDeCompra.setCentroDeCusto(centroDeCusto);
        detalheSolicitacaoDeCompra.setQuantidade(quantidade);
        detalheSolicitacaoDeCompra.setValor(valor);

        return detalheSolicitacaoDeCompra;
    }

    public SolicitacaoDeCompra getSolicitacaoDeCompra() {
        return solicitacaoDeCompra;
    }

    public void setSolicitacaoDeCompra(SolicitacaoDeCompra solicitacaoDeCompra) {
        this.solicitacaoDeCompra = solicitacaoDeCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public CentroDeCusto getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(CentroDeCusto centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
