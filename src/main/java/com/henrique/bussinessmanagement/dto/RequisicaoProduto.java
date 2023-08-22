package com.henrique.bussinessmanagement.dto;

import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.enums.Unidades;
import jakarta.annotation.Nonnull;


public class RequisicaoProduto {

    private String codigo;

    private String unidade;

    private String descricao;

    public Produto toProduto(){
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setUnidade(Unidades.valueOf(unidade));
        produto.setDescricao(descricao);

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
}
