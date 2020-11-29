package com.ikea.warehouse.repository;

import com.ikea.warehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
