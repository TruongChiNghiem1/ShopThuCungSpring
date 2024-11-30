package iuh.fit.se.dtos;

public class RegisterDTO {
	   private String name; // Tên đầy đủ
	    private String username; // Tên tài khoản
	    private String emailAddress; // Địa chỉ email
	    private String password; // Mật khẩu
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
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
