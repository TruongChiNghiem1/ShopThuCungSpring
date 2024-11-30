package iuh.fit.se.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LoginDTO {
	
	@NotNull(message = "Email không được bỏ trống")
	@NotEmpty(message= "Email không được rỗng")
	@Email(message = "Email không đúng định dạng")
	private String emailAddress;
	
	@NotNull(message = "Password không được bỏ trống")
	@NotEmpty(message= "Password không được rỗng")
    private String password;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
