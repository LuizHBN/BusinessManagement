package com.henrique.bussinessmanagement.repository;

import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.SolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoDeCompraRepository extends JpaRepository<SolicitacaoDeCompra, Long> {

    List<SolicitacaoDeCompra> findByCodigo(String codigo);
    List<SolicitacaoDeCompra> findByStatus(Status status);
}
