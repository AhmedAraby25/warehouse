package com.ikea.warehouse.api;

import com.ikea.warehouse.DTO.ProductDTO;
import com.ikea.warehouse.model.Product;
import com.ikea.warehouse.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    List<ProductDTO> getAllProduct() {
        return productService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void sellProduct(@PathVariable Long id) {
        productService.sellProduct(id);
    }

    @GetMapping("/{id}")
    ProductDTO getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }


}
