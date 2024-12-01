package iuh.fit.se.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iuh.fit.se.entities.Category;
import iuh.fit.se.repository.CategoryRepository;
import iuh.fit.se.repository.ProductRepository;
import iuh.fit.se.services.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    
    public boolean hasProducts(Long categoryId) {
        // Kiểm tra xem có sản phẩm nào thuộc về category này không
        return productRepository.countByCategoryId(categoryId) > 0;
    }
}
