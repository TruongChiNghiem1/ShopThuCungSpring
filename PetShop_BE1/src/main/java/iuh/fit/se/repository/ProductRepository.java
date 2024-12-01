package iuh.fit.se.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import iuh.fit.se.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	long countByCategoryId(Long categoryId);
	@Query(value = "SELECT * FROM products WHERE new_price BETWEEN :minPrice AND :maxPrice", nativeQuery = true)
	List<Product> searchProductsByPrice(@Param("minPrice") float minPrice, @Param("maxPrice") float maxPrice);

	@Query(value = "SELECT * FROM products WHERE new_price BETWEEN :minPrice AND :maxPrice AND category_id IN (:categories)", nativeQuery = true)
	List<Product> searchProductsByPriceAndCategories(@Param("minPrice") float minPrice, @Param("maxPrice") float maxPrice, @Param("categories") List<Integer> categories);



}