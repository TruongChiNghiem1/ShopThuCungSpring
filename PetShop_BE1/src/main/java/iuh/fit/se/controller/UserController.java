//package iuh.fit.se.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import iuh.fit.se.entities.User;
//import iuh.fit.se.services.UserService;
//
//public class UserController {
//	 @Autowired
//	    private UserService userService;
//	 
//	@PutMapping("/profile")
//	public ResponseEntity<?> updateProfile(@RequestBody User updatedUser) {
//	    userService.updateUser(updatedUser);
//	    return ResponseEntity.ok("Cập nhật thành công!");
//	}
//}
