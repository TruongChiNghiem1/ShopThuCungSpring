package iuh.fit.se.controller;

import iuh.fit.se.entities.User;
import iuh.fit.se.services.UserService;
import iuh.fit.se.utils.APResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminProductController {
    @GetMapping("/admin/products")
    public String showList() {
        return "adminProduct"; 
    }
    
    @GetMapping("/admin/products/form/{id}")
    public String showFormEdit(@PathVariable int id, Model model) {
    	model.addAttribute("productId", id);
        return "formAdminProduct";
    }

    @GetMapping("/admin/products/form")
    public String showForm() {
        return "formAdminProduct";
    }
}
