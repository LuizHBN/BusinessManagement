package com.henrique.bussinessmanagement.repository;
import com.henrique.bussinessmanagement.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("select p from Produto p where p.codigo like %?1%")
    List<Produto> findByCodigo(String codigo);

    @Query("select p from Produto p where p.descricao like %?1%")
    List<Produto> findByDescricao(String descricao);



}
