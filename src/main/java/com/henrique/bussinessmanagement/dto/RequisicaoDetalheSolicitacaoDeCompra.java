package com.henrique.bussinessmanagement.dto;

import com.henrique.bussinessmanagement.model.CentroDeCusto;
import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RequisicaoDetalheSolicitacaoDeCompra {
    @NotNull(message = "Selecione um produto")
    private Integer idProduto;
    @NotNull(message = "Selecione um centro de custo")
    private Integer idCentroDeCusto;
    @NotBlank(message = "Insíra um valor")
    private String valor;
    @NotNull(message = "Insira a quantidade")
    private double quantidade;


    public DetalheSolicitacaoDeCompra toDetalheSolicitacaoDeCompra(){
        DetalheSolicitacaoDeCompra detalheSolicitacaoDeCompra = new DetalheSolicitacaoDeCompra();

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
