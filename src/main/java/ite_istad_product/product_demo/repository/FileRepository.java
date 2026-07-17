package ite_istad_product.product_demo.repository;

import ite_istad_product.product_demo.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileUpload, Long> {
    Optional<FileUpload> findByName(String name);
}
