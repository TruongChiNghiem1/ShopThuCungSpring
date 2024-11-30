package iuh.fit.se.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double oldPrice;
    private double newPrice;
    private String description;
    private String size;
    private String color;
    private int quantity;
    private boolean isActive;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ElementCollection
    private List<String> images; // Danh sách URL của ảnh

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String name, double oldPrice, double newPrice, String description, String size,
			String color, int quantity, boolean isActive, Category category, List<String> images) {
		super();
		this.id = id;
		this.name = name;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.description = description;
		this.size = size;
		this.color = color;
		this.quantity = quantity;
		this.isActive = isActive;
		this.category = category;
		this.images = images;
	}

	public Product(String name, double oldPrice, double newPrice, String description, String size, String color,
			int quantity, boolean isActive, Category category, List<String> images) {
		super();
		this.name = name;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
		this.description = description;
		this.size = size;
		this.color = color;
		this.quantity = quantity;
		this.isActive = isActive;
		this.category = category;
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}

	public double getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", oldPrice=" + oldPrice + ", newPrice=" + newPrice
				+ ", description=" + description + ", size=" + size + ", color=" + color + ", quantity=" + quantity
				+ ", isActive=" + isActive + ", category=" + category + ", images=" + images + "]";
	}
    
    
}
