package com.henrique.bussinessmanagement.repository;

import com.henrique.bussinessmanagement.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
