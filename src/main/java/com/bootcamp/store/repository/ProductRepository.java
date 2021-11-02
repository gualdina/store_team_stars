package com.bootcamp.store.repository;

import com.bootcamp.store.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
