package iuh.fit.se.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import iuh.fit.se.dtos.UserDTO;
import iuh.fit.se.services.UserService;
import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<Map<String, Object>> updateUser(@Valid @PathVariable Long userId, @RequestBody UserDTO userDTO, BindingResult result) {
		System.out.println(userDTO);
		
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		System.out.println("BE::::" + result);
		if (result.hasErrors()) {
			Map<String, Object> error = new LinkedHashMap<String, Object>();
			result.getFieldErrors().stream().forEach(loi -> {
				error.put(loi.getField(), loi.getDefaultMessage());
			});
			System.out.println("Backend:: " + error);
			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("error", error);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		UserDTO updatedUser = userService.update(userId, userDTO);
		
		if (updatedUser == null) {
			response.put("status", HttpStatus.BAD_REQUEST.value());
			response.put("message", "User not found");
			return ResponseEntity.status(404).body(response);
		}

		response.put("status", HttpStatus.OK.value());
		response.put("data", updatedUser);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
