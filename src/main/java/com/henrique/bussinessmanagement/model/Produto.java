package com.henrique.bussinessmanagement.model;

import com.henrique.bussinessmanagement.model.enums.Unidades;
import com.henrique.bussinessmanagement.model.enums.TipoProduto;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codigo;
    @Column(name = "unidade")
    @Enumerated(EnumType.STRING)
    private Unidades unidade;
    @Column(name = "tipo_produto")
    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;

    private String descricao;


    public Produto() {
    }

    public Produto(int id, String codigo, Unidades unidade, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.unidade = unidade;
        this.descricao = descricao;
    }

    public String codigoByTipo(){
        StringBuilder codigoBuilder = new StringBuilder();

        switch (this.tipoProduto) {
            case ESTOQUE -> codigoBuilder.append("ES");
            case SERVICO -> codigoBuilder.append("SS");
            case COMPRA_DIRETA -> codigoBuilder.append("CD");
        }
        codigoBuilder.append(this.id);

        return codigoBuilder.toString();
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
        this.codigo = codigoByTipo();
    }

    public Unidades getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidades unidade) {
        this.unidade = unidade;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}


