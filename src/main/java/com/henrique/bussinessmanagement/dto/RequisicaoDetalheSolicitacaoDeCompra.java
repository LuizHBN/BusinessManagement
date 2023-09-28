package com.henrique.bussinessmanagement.dto;

import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RequisicaoDetalheSolicitacaoDeCompra {
    @NotNull(message = "Selecione um produto")
    private Integer idProduto;
    @NotNull(message = "Selecione um centro de custo")
    private Integer idCentroDeCusto;
    @NotNull(message = "Insíra um valor")
    private BigDecimal valor;
    @NotNull(message = "Insira a quantidade")
    private BigDecimal quantidade;

    public boolean isValid(){
        if (this.getIdProduto()!= 0){
            if (this.getQuantidade() == null || this.getQuantidade().intValue() == 0){
                return false;
            }if (this.getValor() == null || this.getValor().intValue() == 0){
                return false;
            }
            return this.getIdCentroDeCusto() != null && this.getIdCentroDeCusto() != 0;
        }
        return true;
    }
    public boolean isFirstValid(){
        if (this.getIdProduto() == null || this.getIdProduto() == 0){
            return false;
        }
        if (this.getQuantidade() == null || this.getQuantidade().intValue() == 0){
            return false;
        }if (this.getValor() == null || this.getValor().intValue() == 0){
            return false;
        }
        return this.getIdCentroDeCusto() != null && this.getIdCentroDeCusto() != 0;
}

    public DetalheSolicitacaoDeCompra toDetalheSolicitacaoDeCompra(){
        DetalheSolicitacaoDeCompra detalheSolicitacaoDeCompra = new DetalheSolicitacaoDeCompra();

        detalheSolicitacaoDeCompra.setQuantidade(this.quantidade);
        detalheSolicitacaoDeCompra.setValor(this.valor);

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }
}
