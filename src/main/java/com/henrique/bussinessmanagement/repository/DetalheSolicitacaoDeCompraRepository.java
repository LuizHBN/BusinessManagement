package com.henrique.bussinessmanagement.repository;

import com.henrique.bussinessmanagement.model.CentroDeCusto;
import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.SolicitacaoDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetalheSolicitacaoDeCompraRepository extends JpaRepository<DetalheSolicitacaoDeCompra, Long> {


    List<DetalheSolicitacaoDeCompra> findBySolicitacaoDeCompra(SolicitacaoDeCompra solicitacaoDeCompra);
    @Query("SELECT d FROM DetalheSolicitacaoDeCompra d WHERE d.produto = :produto")
    List<DetalheSolicitacaoDeCompra> findByProduto(Produto produto);
    @Query("SELECT d FROM DetalheSolicitacaoDeCompra d WHERE d.centroDeCusto = :centroDeCusto")
    List<DetalheSolicitacaoDeCompra> findByCentroDeCusto(CentroDeCusto centroDeCusto);
}
