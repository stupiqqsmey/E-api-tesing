package ite_istad_product.product_demo.service;

import ite_istad_product.product_demo.dto.ProductRequest;
import ite_istad_product.product_demo.dto.UpdateProductRequest;
import ite_istad_product.product_demo.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    List<ProductResponse>findAllProducts();
    ProductResponse findProductById(Integer id);
    ProductResponse updateProduct(Integer id, UpdateProductRequest updateProductrequest);
    boolean deleteProduct(int id);
}
