package com.henrique.bussinessmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class DetalheSolicitacaoDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_solicitacao", nullable = false)
    private SolicitacaoDeCompra solicitacaoDeCompra;
    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "id_centro_de_custo", nullable = false)
    private CentroDeCusto centroDeCusto;
    @Column(nullable = false)
    private BigDecimal quantidade;
    @Column(nullable = false)
    private BigDecimal valor;

    private BigDecimal valorTotal;

    public void LinkToNewSolicitacao(SolicitacaoDeCompra solicitacaoDeCompra){

        this.setSolicitacaoDeCompra(solicitacaoDeCompra);
    }

    public DetalheSolicitacaoDeCompra() {
    }

    public DetalheSolicitacaoDeCompra(int id, SolicitacaoDeCompra solicitacaoDeCompra, Produto produto,
                                      CentroDeCusto centroDeCusto, BigDecimal quantidade, BigDecimal valor) {
        this.id = id;
        this.solicitacaoDeCompra = solicitacaoDeCompra;
        this.produto = produto;
        this.centroDeCusto = centroDeCusto;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public BigDecimal getValorUnitario(){
        return this.valor.divide(this.quantidade);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SolicitacaoDeCompra getSolicitacaoDeCompra() {
        return solicitacaoDeCompra;
    }

    public void setSolicitacaoDeCompra(SolicitacaoDeCompra solicitacaoDeCompra) {
        this.solicitacaoDeCompra = solicitacaoDeCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public CentroDeCusto getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(CentroDeCusto centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal() {
        this.valorTotal = this.valor.multiply(this.quantidade) ;
    }
}
