package iuh.fit.se.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
		private Long id;

	    private String name;
	    private String username; 
	    private String emailAddress; 
	    private String password; 
	    private Role role;
	    @JsonIgnore
	    private Set<Order> orders;
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

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



	public User(Long id, String name, String username, String emailAddress, String password, Role role,
				Set<Order> orders) {
			super();
			this.id = id;
			this.name = name;
			this.username = username;
			this.emailAddress = emailAddress;
			this.password = password;
			this.role = role;
			this.orders = orders;
		}

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public Set<Order> getOrders() {
			return orders;
		}

		public void setOrders(Set<Order> orders) {
			this.orders = orders;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", username=" + username + ", emailAddress=" + emailAddress
					+ ", password=" + password + "]";
		}
	

    // Constructor
    
}
