package ite_istad_product.product_demo.repository;

import ite_istad_product.product_demo.entity.Category;
import org.springdoc.core.converters.models.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Boolean existsByName(String name);
    // pagination
    List<Category> findByParentCategoryIsNull(Sort sort );
}
