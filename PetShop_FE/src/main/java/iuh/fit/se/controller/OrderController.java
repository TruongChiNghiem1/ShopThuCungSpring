package iuh.fit.se.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import iuh.fit.se.entities.User;
import iuh.fit.se.services.OrderService;
import iuh.fit.se.utils.APResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/orders")
public class OrderController {
	 @Autowired
	    private OrderService orderService;
	 
	 @GetMapping("/history")
	    public ModelAndView getOrderHistory(HttpSession session) {
	        ModelAndView modelAndView = new ModelAndView();

	      
	        User loggedUser = (User) session.getAttribute("loggedUser");
	        System.out.println("In ra " + loggedUser);
	        if (loggedUser == null) {
	            modelAndView.addObject("message", "Bạn chưa đăng nhập!");
	            modelAndView.setViewName("redirect:/loginPage"); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
	            return modelAndView;
	        }

	
	        Long userId = loggedUser.getId(); 

	     
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
