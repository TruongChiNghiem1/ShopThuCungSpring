package iuh.fit.se.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import iuh.fit.se.entities.Role;
import iuh.fit.se.entities.User;
import iuh.fit.se.services.OrderService;
import iuh.fit.se.services.UserService;
import iuh.fit.se.utils.APResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/admin/users")
	public ModelAndView showList() {
		ModelAndView mav = new ModelAndView("adminUsers");
		APResponse response = userService.findByRole(Role.CUSTOMER);
		if (response.getStatus() == 200) {
			mav.addObject("users", response.getData());
		} else {
			mav.addObject("error", "Failed to load users: " + response.getMessage());
		}
		return mav;
	}

	@GetMapping("/admin/orders/{userId}")
	public ModelAndView getOrderHistory(@PathVariable Long userId) {
		ModelAndView modelAndView = new ModelAndView();
		APResponse response = orderService.getOrderHistory(userId);

		if (response.getStatus() == 200) {
			modelAndView.addObject("orderHistory", response.getData()); 
			modelAndView.setViewName("order-history"); 
		} else {
			modelAndView.addObject("message", "Không thể lấy lịch sử đơn hàng: " + response.getMessage());
			modelAndView.setViewName("error"); 
		}

		return modelAndView;
	}

}
