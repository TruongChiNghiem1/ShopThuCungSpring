package iuh.fit.se.services;

import iuh.fit.se.dtos.LoginDTO;
import iuh.fit.se.dtos.ProductDTO;
import iuh.fit.se.dtos.RegisterDTO;
import iuh.fit.se.dtos.UserDTO;
import iuh.fit.se.entities.User;
import iuh.fit.se.entities.Role;

public interface UserService {
    // Đăng ký tài khoản người dùng
    User registerUser(RegisterDTO registerDTO);
    UserDTO  authenticate(LoginDTO loginDTO);
    UserDTO update(Long id, UserDTO UserDTO);
    boolean changePassword(long userId, String oldPassword, String newPassword) throws Exception;
}
