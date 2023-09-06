package com.henrique.bussinessmanagement.repository;

import com.henrique.bussinessmanagement.model.SolicitacaoDeCompra;
import com.henrique.bussinessmanagement.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolicitacaoDeCompraRepository extends JpaRepository<SolicitacaoDeCompra, Long> {
    @Query("SELECT s FROM SolicitacaoDeCompra s")
    List<SolicitacaoDeCompra> findAll();
    List<SolicitacaoDeCompra> findById(int id);
    List<SolicitacaoDeCompra> findByCodigo(String codigo);
    List<SolicitacaoDeCompra> findByStatus(Status status);
}
