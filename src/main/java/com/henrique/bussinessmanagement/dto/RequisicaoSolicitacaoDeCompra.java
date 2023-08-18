package com.henrique.bussinessmanagement.dto;

import com.henrique.bussinessmanagement.model.CentroDeCusto;
import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.SolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.enums.Status;


import java.math.BigDecimal;
import java.time.LocalDate;

public class RequisicaoSolicitacaoDeCompra {
    private String codigo;
    private LocalDate dataCriacao;
    private Status status;

    public SolicitacaoDeCompra toSolicitacaoDeCompra(){
        SolicitacaoDeCompra solicitacaoDeCompra = new SolicitacaoDeCompra();

        solicitacaoDeCompra.setCodigo(codigo);
        solicitacaoDeCompra.setDataCriacao(dataCriacao);
        solicitacaoDeCompra.setStatus(status);

        return solicitacaoDeCompra;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
