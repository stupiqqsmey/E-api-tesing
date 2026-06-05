package ite_istad_product.product_demo.restcontroller;


import ite_istad_product.product_demo.dto.ProductRequest;
import ite_istad_product.product_demo.dto.ProductResponse;
import ite_istad_product.product_demo.dto.UpdateProductRequest;
import ite_istad_product.product_demo.repository.ProductRepository;
import ite_istad_product.product_demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    /// inject method

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductByID(@PathVariable Integer id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PatchMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Integer id, @RequestBody
    UpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }
}
