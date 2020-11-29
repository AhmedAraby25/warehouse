package com.ikea.warehouse.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products_articles")
@NoArgsConstructor
public class ProductArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "art_id")
    private Article article;

    @Column(name = "amount_of")
    private long amountOf;

    public ProductArticle(Product product, Article article, long amountOf) {
        this.product = product;
        this.article = article;
        this.amountOf = amountOf;
    }
}
