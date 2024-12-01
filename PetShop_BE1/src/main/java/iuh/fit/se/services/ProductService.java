package iuh.fit.se.services;

import iuh.fit.se.entities.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductByFilter(float minPrice, float maxPrice, List<Integer> categories);
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    void updateProductStatus(Long id, boolean isActive);
}
