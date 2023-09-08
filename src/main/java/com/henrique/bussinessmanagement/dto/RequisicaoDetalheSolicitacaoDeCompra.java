package com.henrique.bussinessmanagement.dto;

import com.henrique.bussinessmanagement.model.CentroDeCusto;
import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.Produto;

import java.math.BigDecimal;

public class RequisicaoDetalheSolicitacaoDeCompra {

    private Integer idProduto;
    private Integer idCentroDeCusto;
    private String valor;
    private double quantidade;


    public DetalheSolicitacaoDeCompra toDetalheSolicitacaoDeCompra(Produto produto,CentroDeCusto centroDeCusto){
        DetalheSolicitacaoDeCompra detalheSolicitacaoDeCompra = new DetalheSolicitacaoDeCompra();

        centroDeCusto.setId(idCentroDeCusto);
        detalheSolicitacaoDeCompra.setCentroDeCusto(centroDeCusto);

        produto.setId(idProduto);
        detalheSolicitacaoDeCompra.setProduto(produto);


        detalheSolicitacaoDeCompra.setQuantidade(BigDecimal.valueOf(this.quantidade));
        detalheSolicitacaoDeCompra.setValor(BigDecimal.valueOf(Double.parseDouble(this.valor)));

        return detalheSolicitacaoDeCompra;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdCentroDeCusto() {
        return idCentroDeCusto;
    }

    public void setIdCentroDeCusto(Integer idCentroDeCusto) {
        this.idCentroDeCusto = idCentroDeCusto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
