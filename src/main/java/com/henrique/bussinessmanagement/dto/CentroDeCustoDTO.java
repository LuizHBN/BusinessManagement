package com.henrique.bussinessmanagement.dto;

import com.henrique.bussinessmanagement.model.CentroDeCusto;
import jakarta.validation.constraints.NotBlank;

public class CentroDeCustoDTO {
    private String codigo;
    @NotBlank(message = "O centro de custo deve possuir uma descrição!")
    private String descricao;

    public CentroDeCusto toCentroDeCusto(){
        CentroDeCusto centroDeCusto = new CentroDeCusto();
        centroDeCusto.setDescricao(descricao);


        return centroDeCusto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }



}
