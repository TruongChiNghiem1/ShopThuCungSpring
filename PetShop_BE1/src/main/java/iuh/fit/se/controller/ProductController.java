package iuh.fit.se.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import iuh.fit.se.entities.Category;
import iuh.fit.se.entities.Product;
import iuh.fit.se.services.CategoryService;
import iuh.fit.se.services.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    
    @GetMapping("/find-id/{id}")
    public ResponseEntity<Product> getCategoryById(@PathVariable Long id) {
    	Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build(); // Trả về 404 nếu không tìm thấy
        }
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateProductStatus(@PathVariable Long id, @RequestBody Map<String, Boolean> status) {
        boolean isActive = status.get("isActive");
        productService.updateProductStatus(id, isActive); 
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public Product createProduct(@RequestParam("name") String name,
                                 @RequestParam("oldPrice") double oldPrice,
                                 @RequestParam("newPrice") double newPrice,
                                 @RequestParam("description") String description,
                                 @RequestParam("size") String size,
                                 @RequestParam("color") String color,
                                 @RequestParam("quantity") int quantity,
                                 @RequestParam("category") Long categoryId,
                                 @RequestParam("images") List<MultipartFile> images) {
    	Category cate = categoryService.getCategoryById(categoryId);
        Product product = new Product();
        product.setCategory(cate);
        product.setName(name);
        product.setOldPrice(oldPrice);
        product.setNewPrice(newPrice);
        product.setDescription(description);
        product.setSize(size);
        product.setColor(color);
        product.setQuantity(quantity);
        
        // Xử lý lưu trữ ảnh
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : images) {
            // Giả sử bạn có một phương thức để lưu ảnh và trả về URL
            String imageUrl = saveImage(image);
            imageUrls.add(imageUrl);
        }
        product.setImages(imageUrls);
        
        return productService.saveProduct(product);
    }

//    @PutMapping("/{id}")
//    public Product updateProduct(@PathVariable Long id,
//                                 @RequestParam("name") String name,
//                                 @RequestParam("oldPrice") double oldPrice,
//                                 @RequestParam("newPrice") double newPrice,
//                                 @RequestParam("description") String description,
//                                 @RequestParam("size") String size,
//                                 @RequestParam("color") String color,
//                                 @RequestParam("quantity") int quantity,
//                                 @RequestParam("category") Long categoryId,
//                                 @RequestParam(value = "images", required = false) List<MultipartFile> images) {
//        Product product = productService.getProductById(id);
//        if (product != null) {
//            product.setName(name);
//            product.setOldPrice(oldPrice);
//            product.setNewPrice(newPrice);
//            product.setDescription(description);
//            product.setSize(size);
//            product.setColor(color);
//            product.setQuantity(quantity);
//            
//            // Nếu có ảnh mới, xử lý lưu trữ ảnh
//            if (images != null && !images.isEmpty()) {
//                List<String> imageUrls = new ArrayList<>();
//                for (MultipartFile image : images) {
//                    String imageUrl = saveImage(image);
//                    imageUrls.add(imageUrl);
//                }
//                product.setImages(imageUrls);
//            }
//
//            return productService.saveProduct(product);
//        } else {
//            return null; // Hoặc ném ra ngoại lệ nếu không tìm thấy sản phẩm
//        }
//    }
    
    private final String uploadDir = "D:/JAVA/www/spring/project/SubProject_WWW_JAVA/PetShop_BE1/src/main/resources/static/assets/upload/images/";
    public String saveImage(MultipartFile image) {
        String originalFilename = image.getOriginalFilename();
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        Path filePath = Paths.get(uploadDir, newFileName);

        try {
            Files.createDirectories(filePath.getParent()); // Tạo thư mục nếu chưa tồn tại
            image.transferTo(filePath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Hoặc ném ra ngoại lệ tùy theo yêu cầu
        }

        return "/images/" + newFileName; // Thay đổi đường dẫn theo cấu trúc URL của bạn
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
