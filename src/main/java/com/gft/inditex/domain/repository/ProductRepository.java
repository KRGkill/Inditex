package com.gft.inditex.domain.repository;

import com.gft.inditex.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
