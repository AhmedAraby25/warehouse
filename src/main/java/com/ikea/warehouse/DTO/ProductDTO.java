package com.ikea.warehouse.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductDTO {
    String name;
    List<ArticleDTO> contain_articles;
}
