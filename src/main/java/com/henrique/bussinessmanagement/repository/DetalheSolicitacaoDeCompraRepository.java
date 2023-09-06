package com.henrique.bussinessmanagement.repository;

import com.henrique.bussinessmanagement.model.DetalheSolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.SolicitacaoDeCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetalheSolicitacaoDeCompraRepository extends JpaRepository<DetalheSolicitacaoDeCompra, Long> {


    List<DetalheSolicitacaoDeCompra> findBySolicitacaoDeCompra(SolicitacaoDeCompra solicitacaoDeCompra);

}
