package iuh.fit.se.dtos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import iuh.fit.se.entities.Order;
import iuh.fit.se.entities.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserDTO {
	private Long id;

	@NotNull(message = "Name không được bỏ trống")
	@NotEmpty(message = "Name không được rỗng")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name chỉ được nhập kí tự chữ")
	private String name;

	@NotNull(message = "Username không được bỏ trống")
	@NotEmpty(message = "Username không được rỗng")
	private String username;

	@NotNull(message = "Email address không được bỏ trống")
	@NotEmpty(message = "Email address không được rỗng")
	@Email(message = "Email không đúng định dạng")
	private String emailAddress;

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

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
