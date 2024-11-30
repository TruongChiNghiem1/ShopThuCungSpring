package iuh.fit.se.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iuh.fit.se.dtos.OrderDTO;
import iuh.fit.se.dtos.ProductDTO;
import iuh.fit.se.entities.Order;
import iuh.fit.se.entities.Product;
import iuh.fit.se.repository.OrderRepository;
import iuh.fit.se.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	public ProductDTO toProductDTO(Product product) {
	    ProductDTO dto = new ProductDTO();
	    dto.setId(product.getId());
	    dto.setName(product.getName());
	    dto.setPrice(product.getNewPrice());
	    return dto;
	}

	public OrderDTO toOrderDTO(Order order) {
	    OrderDTO dto = new OrderDTO();
	    dto.setId(order.getId());
	    dto.setOrderDate(order.getOrderDate());
	    dto.setTotalAmount(order.getTotalAmount());

	 
	    dto.setProducts(
	        order.getOrderProducts()
	             .stream()
	             .map(orderProduct -> toProductDTO(orderProduct.getProduct()))
	             .collect(Collectors.toList())
	    );
	    return dto;
	}

	 public List<OrderDTO> getOrdersByUser(Long userId) {
	        List<Order> orders = orderRepository.findByUserId(userId);
	        return orders.stream().map(this::toOrderDTO).collect(Collectors.toList());
	    }
	
}
