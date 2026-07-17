package ite_istad_product.product_demo.repository;


import ite_istad_product.product_demo.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository
        extends JpaRepository<Product, Integer>,
        JpaSpecificationExecutor<Product> {


}