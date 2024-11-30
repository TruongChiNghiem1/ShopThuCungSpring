package iuh.fit.se.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminCategoryController {
    @GetMapping("/admin/categoties")
    public String showList() {
        return "adminCategories"; 
    }

    @GetMapping("/admin/categoties/form/{id}")
    public String showFormEdit(@PathVariable int id, Model model) {
    	model.addAttribute("categoryId", id);
        return "formAdminCategory";
    }
    
    @GetMapping("/admin/categoties/form")
    public String showForm() {
        return "formAdminCategory";
    }
}
