package ite_istad_product.product_demo.repository;

import ite_istad_product.product_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByKeycloakId(String keycloakId);

}
