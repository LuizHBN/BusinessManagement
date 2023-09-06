package com.henrique.bussinessmanagement.dto;

import com.henrique.bussinessmanagement.model.SolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.enums.Status;

public class RequisicaoSolicitacaoDeCompra {
    private String codigo;
    private String status;


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
