package com.henrique.bussinessmanagement.repository;

import com.henrique.bussinessmanagement.model.Produto;
import com.henrique.bussinessmanagement.model.enums.Status;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCodigo(String codigo);

    //TODO Verificar funcionamento do método findByDescricao
    List<Produto> findByDescricao(String descricao);



}
