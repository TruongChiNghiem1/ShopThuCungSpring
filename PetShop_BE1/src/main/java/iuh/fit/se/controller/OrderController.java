package iuh.fit.se.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import iuh.fit.se.dtos.OrderDTO;

import iuh.fit.se.services.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping("/orderhistory/{userId}")
	public ResponseEntity<Map<String, Object>> getOrderHistory(@PathVariable Long userId) {

		System.out.println(userId);
		Map<String, Object> response = new LinkedHashMap<String, Object>();

		List<OrderDTO> orderHistory = orderService.getOrdersByUser(userId);

		if (orderHistory != null) {
			response.put("status", HttpStatus.OK.value());
			response.put("data", orderHistory);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response.put("status", HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}
	}
	
	@PostMapping("/orders")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDTO orderDTO) {
        // Xử lý đơn hàng tại đây (lưu vào database, v.v.)
        try {
            orderService.saveOrder(orderDTO);
            return ResponseEntity.ok(Map.of("status", HttpStatus.OK.value(), "message", "Order placed successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("status", HttpStatus.INTERNAL_SERVER_ERROR.value(), "message", "Failed to place order."));
        }
    }

}
