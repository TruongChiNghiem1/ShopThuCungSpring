package iuh.fit.se.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iuh.fit.se.dtos.LoginDTO;
import iuh.fit.se.dtos.RegisterDTO;
import iuh.fit.se.dtos.UserDTO;
import iuh.fit.se.entities.User;
import iuh.fit.se.services.UserService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody RegisterDTO registerDTO,
			BindingResult result) {
		Map<String, Object> response = new LinkedHashMap<>();
		if (result.hasErrors()) {
			Map<String, Object> error = new LinkedHashMap<String, Object>();
			result.getFieldErrors().stream().forEach(loi -> {
				error.put(loi.getField(), loi.getDefaultMessage());
			});
			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("error", error);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		try {
			User user = userService.registerUser(registerDTO);
			response.put("status", HttpStatus.OK.value());
			response.put("data", user);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			response.put("status", HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) {
        Map<String, Object> response = new LinkedHashMap<>();
        if (result.hasErrors()) {
        	Map<String, Object> error = new LinkedHashMap<String, Object>();
        	result.getFieldErrors().stream().forEach(loi -> {
				error.put(loi.getField(), loi.getDefaultMessage());
			});
			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("error",error);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            UserDTO userDTO = userService.authenticate(loginDTO);
            if (userDTO != null) {
                response.put("status", HttpStatus.OK.value());
                response.put("data", userDTO); 
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                response.put("status", HttpStatus.UNAUTHORIZED.value());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
	
	 @PostMapping("/change-password")
	    public ResponseEntity<Map<String, Object>> changePassword(@RequestBody Map<String, String> payload) {
	        Map<String, Object> response = new LinkedHashMap<>();
	        try {
	            int userId = Integer.parseInt(payload.get("id"));
	            String oldPassword = payload.get("oldPassword");
	            String newPassword = payload.get("newPassword");
	            String confirmPassword = payload.get("confirmPassword");

	            // Kiểm tra mật khẩu mới và xác nhận mật khẩu
	            if (!newPassword.equals(confirmPassword)) {
	                response.put("status", HttpStatus.BAD_REQUEST.value());
	                response.put("message", "New password and confirm password do not match.");
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	            }

	            // Gọi service để đổi mật khẩu
	            boolean isPasswordChanged = userService.changePassword(userId, oldPassword, newPassword);

	            if (isPasswordChanged) {
	                response.put("status", HttpStatus.OK.value());
	                response.put("message", "Password changed successfully.");
	                return ResponseEntity.status(HttpStatus.OK).body(response);
	            } else {
	                response.put("status", HttpStatus.BAD_REQUEST.value());
	                response.put("message", "Old password is incorrect.");
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	            }
	        } catch (Exception e) {
	            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	            response.put("message", "An error occurred: " + e.getMessage());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	    }

}
