package com.ikea.warehouse.services;

import com.ikea.warehouse.DTO.ArticleDTO;
import com.ikea.warehouse.DTO.ProductDTO;
import com.ikea.warehouse.exceptions.ObjectNotFoundException;
import com.ikea.warehouse.model.Article;
import com.ikea.warehouse.model.Product;
import com.ikea.warehouse.model.ProductArticle;
import com.ikea.warehouse.repository.ArticleRepository;
import com.ikea.warehouse.repository.ProductArticleRepository;
import com.ikea.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductArticleRepository productArticleRepository;
    private final ArticleRepository articleRepository;

    public ProductService(ProductRepository productRepository,
                          ArticleRepository articleRepository,
                          ProductArticleRepository productArticleRepository) {
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
        this.productArticleRepository = productArticleRepository;
    }

    public List<ProductDTO> getAll() {
        List<Product> productList = this.productRepository.findAll();

        return productList.stream().map(product -> {
            List<ProductArticle> productArticlesList = productArticleRepository.findByProduct(product);
            List<ArticleDTO> articleDTOList = productArticlesList.stream().map(productArticle ->
                    new ArticleDTO(productArticle.getArticle().getId(), productArticle.getAmountOf()))
                    .collect(Collectors.toList());

            return new ProductDTO(product.getName(), articleDTOList);
        }).collect(Collectors.toList());
    }

    @Transactional
    public void sellProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Product", id));

        List<ProductArticle> productArticleList = productArticleRepository.findByProduct(product);

        productArticleList.forEach(productArticle -> {
            Article article = productArticle.getArticle();
            article.setStock(article.getStock() - productArticle.getAmountOf());//TODO Handle if the amountOf greater than stock!
            articleRepository.save(article);
            productArticleRepository.delete(productArticle);//TODO regarding the business data, should be soft delete
        });

        this.productRepository.delete(product);
    }

    public ProductDTO getProduct(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Product", id));

        List<ProductArticle> productArticlesList = productArticleRepository.findByProduct(product);

        List<ArticleDTO> articleDTOList = productArticlesList.stream().map(productArticle ->
                new ArticleDTO(productArticle.getArticle().getId(), productArticle.getAmountOf()))
                .collect(Collectors.toList());

        return new ProductDTO(product.getName() , articleDTOList);
    }

}
