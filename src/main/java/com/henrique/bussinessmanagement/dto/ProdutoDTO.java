package com.henrique.bussinessmanagement.dto;

import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.enums.TipoProduto;
import com.henrique.bussinessmanagement.model.enums.Unidades;
import jakarta.validation.constraints.NotBlank;


public class ProdutoDTO {


    private String codigo;
    @NotBlank(message = "O produto deve possuir uma unidade!")
    private String unidade;

    @NotBlank(message = "O produto deve possuir uma descrição!")
    private String descricao;
    @NotBlank(message = "O produto deve possuir uma categoria!")
    private String tipoProduto;

    public Produto toProduto(){
        Produto produto = new Produto();
        produto.setUnidade(Unidades.valueOf(unidade));
        produto.setDescricao(descricao);
        produto.setTipoProduto(TipoProduto.valueOf(tipoProduto));
        produto.setCodigo();

        return produto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}
