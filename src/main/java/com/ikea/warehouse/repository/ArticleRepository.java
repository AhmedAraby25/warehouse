package com.ikea.warehouse.repository;

import com.ikea.warehouse.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
