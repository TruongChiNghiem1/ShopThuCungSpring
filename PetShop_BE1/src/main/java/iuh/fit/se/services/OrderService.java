package iuh.fit.se.services;

import java.util.List;

import iuh.fit.se.dtos.OrderDTO;

public interface OrderService {
	public List<OrderDTO> getOrdersByUser(Long userId);
	void saveOrder(OrderDTO orderDTO);
}
