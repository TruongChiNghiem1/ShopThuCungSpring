package iuh.fit.se.entities;

public class User {
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
		public User(String name, String username, String emailAddress, String password) {
			super();
			this.name = name;
			this.username = username;
			this.emailAddress = emailAddress;
			this.password = password;
		}
		@Override
		public String toString() {
			return "User [name=" + name + ", username=" + username + ", emailAddress=" + emailAddress + ", password="
					+ password + "]";
		}

    // Constructor
    
}
