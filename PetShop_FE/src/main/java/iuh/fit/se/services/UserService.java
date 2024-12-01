package iuh.fit.se.services;

import iuh.fit.se.entities.User;
import iuh.fit.se.utils.APResponse;

public interface UserService {

	public APResponse registerUser(User user);
	public APResponse loginUser(User user);
	APResponse updateUser(Long userId, User user);
	APResponse changePasswordUser(long id, String oldPassword, String newPassword, String confirmPassword);


  
}
