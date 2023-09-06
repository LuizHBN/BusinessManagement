package com.henrique.bussinessmanagement.dto;

import com.henrique.bussinessmanagement.controller.ProdutoController;
import com.henrique.bussinessmanagement.model.CentroDeCusto;
import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.SolicitacaoDeCompra;
import com.henrique.bussinessmanagement.repository.CentroDeCustoRepository;
import com.henrique.bussinessmanagement.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class RequisicaoDetalheSolicitacaoDeCompra {

    private Integer idProduto;
    private Integer idCentroDeCusto;
    private int valor;
    private int quantidade;


    public DetalheSolicitacaoDeCompra toDetalheSolicitacaoDeCompra(Produto produto,CentroDeCusto centroDeCusto){
        DetalheSolicitacaoDeCompra detalheSolicitacaoDeCompra = new DetalheSolicitacaoDeCompra();

        centroDeCusto.setId(idCentroDeCusto);
        detalheSolicitacaoDeCompra.setCentroDeCusto(centroDeCusto);

        produto.setId(idProduto);
        detalheSolicitacaoDeCompra.setProduto(produto);

        detalheSolicitacaoDeCompra.setQuantidade(BigDecimal.valueOf(this.quantidade));
        detalheSolicitacaoDeCompra.setValor(BigDecimal.valueOf(this.valor));

        return detalheSolicitacaoDeCompra;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdCentroDeCusto() {
        return idCentroDeCusto;
    }

    public void setIdCentroDeCusto(Integer idCentroDeCusto) {
        this.idCentroDeCusto = idCentroDeCusto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
