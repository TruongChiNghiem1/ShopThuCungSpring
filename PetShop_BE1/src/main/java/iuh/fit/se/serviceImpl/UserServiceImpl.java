package iuh.fit.se.serviceImpl;

import iuh.fit.se.dtos.LoginDTO;
import iuh.fit.se.dtos.RegisterDTO;
import iuh.fit.se.dtos.UserDTO;
import iuh.fit.se.entities.Role;
import iuh.fit.se.entities.User;
import iuh.fit.se.repository.UserRepository;
import iuh.fit.se.services.UserService;
import jakarta.transaction.Transactional;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    UserRepository userRepository;
	
	@Autowired
    ModelMapper modelMapper;


    private RegisterDTO convertToDTO(User user) {
        return modelMapper.map(user, RegisterDTO.class);
    }


    private User convertToEntity(RegisterDTO registerDTO) {
        return modelMapper.map(registerDTO, User.class);
    }
   
    @Transactional
    @Override
    public User registerUser(RegisterDTO registerDTO) {
      
        User user = this.convertToEntity(registerDTO);
        user.setRole(Role.CUSTOMER);
     
        return userRepository.save(user);
    }

	@Override
	public UserDTO  authenticate(LoginDTO loginDTO) {
		
		 String email = loginDTO.getEmailAddress();
		    String password = loginDTO.getPassword();

		
		    User user = userRepository.findByEmailAddress(email);

		    if (user != null && user.getPassword().equals(password)) {
		      
		        UserDTO userDTO = new UserDTO();
		        userDTO.setId(user.getId());
		        userDTO.setEmailAddress(user.getEmailAddress());
		        userDTO.setName(user.getName());
		        userDTO.setUsername(user.getUsername());
		        userDTO.setRole(user.getRole());
		        return userDTO;
		    }
		    return null;
	}

	@Override
	public UserDTO update(Long id, UserDTO UserDTO) {
		// TODO Auto-generated method stub
		 User existingUser = userRepository.findById(id).orElse(null);

		    if (existingUser != null) {
		       
		        existingUser.setName(UserDTO.getName());
		        existingUser.setEmailAddress(UserDTO.getEmailAddress());
		        existingUser.setUsername(UserDTO.getUsername());

		        
		        existingUser = userRepository.save(existingUser);

		   
		        return modelMapper.map(existingUser, UserDTO.class);
		    }
		    
	
		    return null;
	}
	
	
	@Override
    public List<User> findByRole(Role role) {
        return userRepository.findByRole(role);
    }
	
	@Override
    public boolean changePassword(long userId, String oldPassword, String newPassword) throws Exception {
        // Tìm user theo ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found"));

        // Kiểm tra mật khẩu cũ
        if (!user.getPassword().equals(oldPassword)) {
            return false; // Mật khẩu cũ không khớp
        }

        // Cập nhật mật khẩu mới
        user.setPassword(newPassword);
        userRepository.save(user);
        return true; // Đổi mật khẩu thành công
    }


}
