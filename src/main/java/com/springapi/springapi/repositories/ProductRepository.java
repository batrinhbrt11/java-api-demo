package com.springapi.springapi.repositories;

import java.util.List;

import com.springapi.springapi.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByProductName(String productName);
}
