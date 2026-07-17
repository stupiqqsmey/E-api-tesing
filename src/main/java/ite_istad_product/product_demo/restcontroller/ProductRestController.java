package ite_istad_product.product_demo.restcontroller;

import ite_istad_product.product_demo.dto.product.ProductFilter;
import ite_istad_product.product_demo.dto.product.ProductRequest;
import ite_istad_product.product_demo.dto.product.ProductResponse;
import ite_istad_product.product_demo.dto.product.UpdateProductRequest;
import ite_istad_product.product_demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductResponse> getProducts(
            Pageable pageable,
            ProductFilter filter
    ) {
        return productService.findAllProducts(pageable, filter);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(
            @PathVariable Integer id
    ) {
        return productService.findProductById(id);
    }

    @PostMapping
    public ProductResponse createProduct(
            @Valid @RequestBody ProductRequest request
    ) {
        return productService.createProduct(request);
    }

    @PatchMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateProductRequest request
    ) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(
            @PathVariable Integer id
    ) {
        productService.deleteProduct(id);
    }
}