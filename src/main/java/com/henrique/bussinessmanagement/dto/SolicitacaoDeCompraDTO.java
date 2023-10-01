package com.henrique.bussinessmanagement.dto;

public class SolicitacaoDeCompraDTO {
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
