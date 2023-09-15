package com.henrique.bussinessmanagement.repository;

import com.henrique.bussinessmanagement.model.CentroDeCusto;
import com.henrique.bussinessmanagement.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long> {

    @Query("select c from CentroDeCusto c where c.codigo like %?1%")
    List<CentroDeCusto> findByCodigo(String codigo);

    @Query("select c from CentroDeCusto c where c.descricao like %?1%")
    List<CentroDeCusto> findByDescricao(String descricao);
    @Query("select c from CentroDeCusto c where c.id = ?1")
    CentroDeCusto findById(int id);

}
