package iuh.fit.se.services;

import iuh.fit.se.entities.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    void updateProductStatus(Long id, boolean isActive);
}
