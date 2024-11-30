package iuh.fit.se.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import iuh.fit.se.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}