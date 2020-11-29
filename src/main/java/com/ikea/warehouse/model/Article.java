package com.ikea.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "articles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "art_id")
    Long id;

    private String name;

    private long stock;
}
