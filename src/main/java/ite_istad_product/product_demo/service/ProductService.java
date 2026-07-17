package ite_istad_product.product_demo.service;

import ite_istad_product.product_demo.dto.product.ProductFilter;
import ite_istad_product.product_demo.dto.product.ProductRequest;
import ite_istad_product.product_demo.dto.product.ProductResponse;
import ite_istad_product.product_demo.dto.product.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    Page<ProductResponse> findAllProducts(Pageable pageable, ProductFilter filter);

    ProductResponse findProductById(Integer id);

    ProductResponse updateProduct(Integer id, UpdateProductRequest request);

    void deleteProduct(Integer id);

}