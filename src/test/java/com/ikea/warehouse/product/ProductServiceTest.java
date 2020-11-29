package com.ikea.warehouse.product;

import com.ikea.warehouse.DTO.ProductDTO;
import com.ikea.warehouse.repository.ProductRepository;
import com.ikea.warehouse.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    //TODO I'll depend here on the loaded data, but would be better if I present dummy data to test here.

    @Test
    public void testGetAllProduct() {
        List<ProductDTO> all = productService.getAll();
        assertThat(all).isNotEmpty();
        assertThat(all.size()).isEqualTo(2);
    }
}
