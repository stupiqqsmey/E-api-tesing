package ite_istad_product.product_demo.restcontroller;

import ite_istad_product.product_demo.dto.product.ProductRequest;
import ite_istad_product.product_demo.dto.product.ProductResponse;
import ite_istad_product.product_demo.dto.product.UpdateProductRequest;
import ite_istad_product.product_demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductResponse> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword
    ) {
        return productService.findAllProducts(page, size, keyword);
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
    public ProductResponse updateProduct(
            @PathVariable Integer id,
            @RequestBody UpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }
}