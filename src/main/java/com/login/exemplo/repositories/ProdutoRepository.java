package com.login.exemplo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.login.exemplo.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
