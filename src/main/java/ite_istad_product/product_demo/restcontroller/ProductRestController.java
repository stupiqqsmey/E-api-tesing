package ite_istad_product.product_demo.restcontroller;

import ite_istad_product.product_demo.dto.ProductRequest;
import ite_istad_product.product_demo.dto.ProductResponse;
import ite_istad_product.product_demo.dto.UpdateProductRequest;
import ite_istad_product.product_demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductRestController {

    // យើង Inject តែ Service បានហើយ (លុប Repository ចេញព្រោះ Controller មិនគួរហៅ Repository ផ្ទាល់ទេ)
    private final ProductService productService;

    // ១. កែប្រែពី List ទៅ Page និងបន្ថែម @RequestParam សម្រាប់ Page, Size និង Keyword
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

    // ២. បន្ថែម API សម្រាប់ធ្វើការលុប (Delete)
    @DeleteMapping("/{id}")
    public Boolean deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }
}