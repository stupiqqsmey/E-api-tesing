package ite_istad_product.product_demo.repository;

import ite_istad_product.product_demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryNew
        extends JpaRepository<Product, Integer> {
}