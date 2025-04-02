package com.example.back_front.repository;

import com.example.back_front.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<Produto, Long > {
}
