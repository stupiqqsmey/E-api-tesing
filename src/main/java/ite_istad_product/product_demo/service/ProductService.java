package ite_istad_product.product_demo.service;

import ite_istad_product.product_demo.dto.product.ProductRequest;
import ite_istad_product.product_demo.dto.product.UpdateProductRequest;
import ite_istad_product.product_demo.dto.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    Page<ProductResponse> findAllProducts(int page, int size, String keyword);
    ProductResponse findProductById(Integer id);
    ProductResponse updateProduct(Integer id, UpdateProductRequest updateProductrequest);
    Boolean deleteProduct(Integer id);
}