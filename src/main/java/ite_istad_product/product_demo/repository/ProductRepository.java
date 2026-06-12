package ite_istad_product.product_demo.repository;

import ite_istad_product.product_demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
}