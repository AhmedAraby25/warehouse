package com.ikea.warehouse.repository;

import com.ikea.warehouse.model.Product;
import com.ikea.warehouse.model.ProductArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductArticleRepository extends JpaRepository<ProductArticle, Long> {
    List<ProductArticle> findByProduct(Product product);
}
