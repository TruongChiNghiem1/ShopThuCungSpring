package iuh.fit.se.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import iuh.fit.se.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}