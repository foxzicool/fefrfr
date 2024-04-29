package com.example.demo.repository;

import com.example.demo.model.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDataRepository extends JpaRepository<ProductData, Long> {
}
