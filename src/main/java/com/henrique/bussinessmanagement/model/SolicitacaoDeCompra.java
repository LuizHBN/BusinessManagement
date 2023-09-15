package com.henrique.bussinessmanagement.model;

import com.henrique.bussinessmanagement.model.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class SolicitacaoDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String codigo;
    @Column(nullable = false)
    private LocalDate dataCriacao;
    @Column(nullable = false)
    @Enumerated
    private Status status;

    public SolicitacaoDeCompra() {
        this.setDataCriacao(LocalDate.now());
        this.setStatus(Status.CRIADA);
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

    public void setCodigo() {
        this.codigo = "SC" + this.id;
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
