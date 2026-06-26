package ite_istad_product.product_demo.repository;

import ite_istad_product.product_demo.dto.tag.TagResponse;
import ite_istad_product.product_demo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
