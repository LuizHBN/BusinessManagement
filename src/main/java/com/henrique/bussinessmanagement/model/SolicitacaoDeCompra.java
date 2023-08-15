package com.henrique.bussinessmanagement.model;

import com.henrique.bussinessmanagement.model.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class SolicitacaoDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codigo;
    private LocalDate dataCriacao;
    @Enumerated
    private Status status;

    public SolicitacaoDeCompra() {
    }
    public SolicitacaoDeCompra(int id, String codigo, LocalDate dataCriacao, Status status) {
        this.id = id;
        this.codigo = codigo;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
