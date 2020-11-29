package com.ikea.warehouse;

import com.ikea.warehouse.exceptions.ObjectNotFoundException;
import com.ikea.warehouse.model.Article;
import com.ikea.warehouse.model.Product;
import com.ikea.warehouse.model.ProductArticle;
import com.ikea.warehouse.repository.ArticleRepository;
import com.ikea.warehouse.repository.ProductArticleRepository;
import com.ikea.warehouse.repository.ProductRepository;
import com.ikea.warehouse.services.ProductService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class LoadData {
    @Bean
    CommandLineRunner initData(ArticleRepository articleRepository, ProductRepository productRepository, ProductArticleRepository productArticleRepository, ProductService productService) {
        JSONParser jsonParser = new JSONParser();
        return args -> {
            try {
                JSONArray inventoryList = getJSONArray(jsonParser, "classpath:data/inventory.json", "inventory");
                inventoryList.forEach(article -> saveArticleToDB(articleRepository, (JSONObject) article));

                JSONArray productList = getJSONArray(jsonParser, "classpath:data/products.json", "products");
                productList.forEach(article -> saveProductToDB(productRepository, articleRepository
                        , productArticleRepository, (JSONObject) article));

                //List<ProductDTO> list = productService.getAll();
                //log the loaded data
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        };
    }

    private JSONArray getJSONArray(JSONParser jsonParser, String filePath, String tagName)
            throws IOException, ParseException {
        FileReader reader = new FileReader(ResourceUtils.getFile(filePath));
        //Read JSON file
        JSONObject obj = (JSONObject) jsonParser.parse(reader);
        return (JSONArray) obj.get(tagName);
    }

    @Transactional
    void saveArticleToDB(ArticleRepository repository, JSONObject article) {
        repository.save(new Article(Long.parseLong(String.valueOf(article.get("art_id"))),
                String.valueOf(article.get("name")),
                Long.parseLong(String.valueOf(article.get("stock")))));
    }

    @Transactional
    void saveProductToDB(ProductRepository productRepository,
                         ArticleRepository articleRepository,
                         ProductArticleRepository productArticleRepository,
                         JSONObject product) {

        Product newProduct = productRepository.save(new Product(String.valueOf(product.get("name"))));

        JSONArray articles = (JSONArray) product.get("contain_articles");

        //save to product_article table (product_id, article_id)
        articles.forEach(article -> {
            JSONObject articleJSONObject = (JSONObject) article;
            long art_id = Long.parseLong(String.valueOf(articleJSONObject.get("art_id")));
            long amount_of = Long.parseLong(String.valueOf(articleJSONObject.get("amount_of")));
            Article newArticle = articleRepository.findById(art_id).orElseThrow(() -> new ObjectNotFoundException("Article", art_id));
            productArticleRepository.save(new ProductArticle(newProduct, newArticle, amount_of));
        });
    }

}
