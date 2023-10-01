package com.henrique.bussinessmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class CentroDeCusto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String codigo;
    @Column(nullable = false)
    private String descricao;

    public CentroDeCusto() {
    }

    public CentroDeCusto(int id, String codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String codigoByTipo(){
        String codigo = "CC" +
                this.id;

        return codigo;
    }
    @Override
    public String toString(){
        return this.getDescricao();
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
        codigo = codigoByTipo();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
