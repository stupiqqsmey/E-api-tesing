package ite_istad_product.product_demo.repository;

import ite_istad_product.product_demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryNew extends JpaRepository<Category,Integer> {
    Boolean existsByName(String name);
}
