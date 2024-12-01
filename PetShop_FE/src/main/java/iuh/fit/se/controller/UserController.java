package iuh.fit.se.controller;

import iuh.fit.se.entities.User;
import iuh.fit.se.services.UserService;
import iuh.fit.se.utils.APResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping
    public String showHomePage() {
        return "index"; 
    }
    
    @GetMapping("/loginPage")
    public String goToLoginPage() {
        return "login";  
    } 
    
    @GetMapping("/registrationPage")
    public String goToRegistrationPage(Model model) {
    	  model.addAttribute("user", new User());
        return "registration"; 
    }
    
    @GetMapping("/account")
    public ModelAndView  goToAccountPage(HttpSession session) {
    	  ModelAndView modelAndView = new ModelAndView("account");
    	    
    	    // Giả sử bạn lưu thông tin người dùng vào session sau khi đăng nhập thành công
    	    User loggedUser = (User) session.getAttribute("loggedUser");

    	    if (loggedUser != null) {
    	        modelAndView.addObject("user", loggedUser);
    	    } else {
    	        modelAndView.addObject("message", "Bạn chưa đăng nhập!");
    	        modelAndView.setViewName("redirect:/loginPage");
    	    }
    	    
    	    return modelAndView;
    }
    
    @GetMapping("/userinfo")
    public ModelAndView goToUserInfoPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("userinfo"); 
        ObjectMapper objectMapper = new ObjectMapper();
        User loggedUser = objectMapper.convertValue(session.getAttribute("loggedUser"), User.class);

        if (loggedUser != null) {
            modelAndView.addObject("user", loggedUser);
        } else {
            modelAndView.addObject("message", "Bạn chưa đăng nhập!");
            modelAndView.setViewName("redirect:/loginPage");
        }

        return modelAndView;
    }

    
    @PostMapping("/register")
    public ModelAndView registerUser(User user) {
        APResponse response = userService.registerUser(user);
        ModelAndView modelAndView = new ModelAndView();

        if (response.getStatus() == 200) {
            modelAndView.addObject("message", "Đăng ký thành công!");
            modelAndView.setViewName("redirect:/loginPage");
        } else {
            modelAndView.addObject("message", "Đăng ký thất bại!");
            modelAndView.addObject("errors", response.getError());  
            System.out.println(response.getError());
            modelAndView.addObject("user", user);
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }



   
    @PostMapping("/login")
    public ModelAndView loginUser(@RequestParam String emailAddress, @RequestParam String password, HttpSession session) {
       
        User user = new User();
        user.setEmailAddress(emailAddress);
        user.setPassword(password);
        APResponse response = userService.loginUser(user);

        ModelAndView modelAndView = new ModelAndView();
        if (response.getStatus() == 200) {
            // Nếu đăng nhập thành công
            modelAndView.setViewName("index");
            modelAndView.addObject("message", "Đăng nhập thành công!");
            modelAndView.addObject("loggedIn", true);
            session.setAttribute("loggedUser", response.getData());
        } else {
            // Nếu đăng nhập thất bại
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "Đăng nhập thất bại! " + response.getMessage());
            modelAndView.addObject("loggedIn", false);
            modelAndView.addObject("errors", response.getError());
        }
        return modelAndView;
    }
    
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate(); // Xóa tất cả dữ liệu trong session
        return new ModelAndView("redirect:/loginPage");
    }
    
    @PostMapping("/updateUser")
    public ModelAndView updateUser(@RequestParam Long userId, @ModelAttribute User user, HttpSession session) {
        System.out.println(user.getName());
        System.out.println(user.getEmailAddress());
        System.out.println(user.getUsername());
    	APResponse response = userService.updateUser(userId, user);
        ModelAndView modelAndView = new ModelAndView();

        if (response.getStatus() == 200) {
            modelAndView.setViewName("account");
            modelAndView.addObject("message", "Cập nhật thông tin thành công!");
            modelAndView.addObject("user", response.getData());
            session.setAttribute("loggedUser", response.getData());
        } else {
            modelAndView.setViewName("userinfo");
            modelAndView.addObject("errors", response.getError());
           
        }
        return modelAndView;
    }
    
    @PostMapping("/changePassword")
	public ModelAndView changePasswordUser(
	        @RequestParam String currentPassword,
	        @RequestParam String newPassword,
	        @RequestParam String confirmPassword,
	        HttpSession session) {

	    User loggedUser = (User) session.getAttribute("loggedUser");
	    ModelAndView modelAndView = new ModelAndView();

	    // Kiểm tra xem người dùng có đang đăng nhập không
	    if (loggedUser == null) {
	        modelAndView.setViewName("redirect:/loginPage");
	        modelAndView.addObject("message", "Please, login before change password");
	        return modelAndView;
	    }

	    // Gửi yêu cầu đổi mật khẩu
	    APResponse response = userService.changePasswordUser(
	            loggedUser.getId(), currentPassword, newPassword, confirmPassword);

	    if (response.getStatus() == 200) {
	        modelAndView.setViewName("change-password");
	        modelAndView.addObject("message", "Change password successfully!");
	    } else {
	        modelAndView.setViewName("change-password");
	        modelAndView.addObject("message", "fail to change: " + response.getMessage());
	    }

	    return modelAndView;
	}
    
    @GetMapping("/ChangePasswordPage")
	public String goToChangePasswordPage() {
		return "change-password";
	}


}
