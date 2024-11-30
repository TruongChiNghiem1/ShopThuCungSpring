package iuh.fit.se.controller;

import iuh.fit.se.entities.User;
import iuh.fit.se.services.UserService;
import iuh.fit.se.utils.APResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String showHomePage() {
        return "index"; 
    }
    
    @GetMapping("/loginPage")
    public String goToLoginPage() {
        return "login";  // Trả về view "login.html"
    } 
    
    @GetMapping("/registrationPage")
    public String goToRegistrationPage() {
        return "registration";  // Trả về view "registration.html"
    }
    @GetMapping("/cart")
    public String gotoCardPage() {
        return "cart";  
    }
    @GetMapping("/account")
    public String gotoAccountPage() {
        return "account"; 
    }
    
    // Đăng ký người dùng
    @PostMapping("/register")
    public ModelAndView registerUser( User user) {
        // Kiểm tra xem người dùng đã tồn tại chưa
    	System.out.println("User details: " + user);
        APResponse response;
        response = userService.registerUser(user);
       
         
        return new ModelAndView("redirect:/loginPage");
    }


    // Đăng nhập người dùng
    @PostMapping("/login")
    public ModelAndView loginUser(@RequestParam String username, @RequestParam String password) {
        APResponse response = userService.loginUser(username, password);

        ModelAndView modelAndView = new ModelAndView();
        if (response.getStatus() == 200) {
            // Nếu đăng nhập thành công, hiển thị trang chính
            modelAndView.setViewName("home"); // Tên view chính
            modelAndView.addObject("message", "Đăng nhập thành công!");
        } else {
            // Nếu đăng nhập thất bại
            modelAndView.setViewName("loginError"); // Tên view lỗi đăng nhập
            modelAndView.addObject("message", "Đăng nhập thất bại! " + response.getMessage());
        }
        return modelAndView;
    }
}
