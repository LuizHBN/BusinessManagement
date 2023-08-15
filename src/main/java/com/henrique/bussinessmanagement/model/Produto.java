package com.henrique.bussinessmanagement.model;

import com.henrique.bussinessmanagement.model.enums.Unidades;
import jakarta.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codigo;
    @Enumerated
    private Unidades unidade;
    private String descricao;

    public Produto() {
    }

    public Produto(int id, String codigo, Unidades unidade, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.unidade = unidade;
        this.descricao = descricao;
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

    public Unidades getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidades unidade) {
        this.unidade = unidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
