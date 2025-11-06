package com.login.exemplo.repositories;

import com.login.exemplo.entity.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutorRepository extends JpaRepository<Produtor, Integer> {
}