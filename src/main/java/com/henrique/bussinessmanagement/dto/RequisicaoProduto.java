package com.henrique.bussinessmanagement.dto;

import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.enums.Unidades;
import jakarta.persistence.Enumerated;

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
}
