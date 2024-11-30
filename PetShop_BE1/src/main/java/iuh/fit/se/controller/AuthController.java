//package iuh.fit.se.controller;
//
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.RepositoryRestController;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//
//import iuh.fit.se.dtos.LoginDTO;
//import iuh.fit.se.dtos.RegisterDTO;
//import iuh.fit.se.services.UserService;
//
//@RepositoryRestController
//public class AuthController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDTO loginDTO) {
//        boolean isValid = userService.validateLogin(loginDTO.getEmail(), loginDTO.getPassword());
//        Map<String, Object> response = new LinkedHashMap<String, Object>();
//        if (isValid) {
//            response.put("message", "Đăng nhập thành công!");
//            return ResponseEntity.ok(response);
//        }
//        response.put("message", "Email hoặc mật khẩu không đúng!");
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterDTO registerDTO) {
//    	Map<String, Object> response = new LinkedHashMap<String, Object>();
//      
//    			System.out.println(registerDTO);
//    			     
//            	response.put("status", HttpStatus.OK.value());
//                response.put("data", userService.registerUser(registerDTO));
//                return ResponseEntity.status(HttpStatus.OK.value()).body(response);
//
//        
//    }
//}
