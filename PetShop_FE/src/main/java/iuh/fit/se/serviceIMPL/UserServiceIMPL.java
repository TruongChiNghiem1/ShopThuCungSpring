package iuh.fit.se.serviceIMPL;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import iuh.fit.se.entities.User;
import iuh.fit.se.services.UserService;
import iuh.fit.se.utils.APResponse;

@Service
public class UserServiceIMPL implements UserService {

	private RestClient restClient;
	private ObjectMapper objectMapper;
	private static final String diaChi = "http://localhost:9998/api";

	
	
	public UserServiceIMPL(RestClient restClient, ObjectMapper objectMapper) {
		super();
		this.restClient = restClient;
		this.objectMapper = objectMapper;
	}
	
	@Override
	public APResponse registerUser(User user) {
		
	       
	        return restClient.post()
	                .uri(diaChi + "/register")  
	                .accept(MediaType.APPLICATION_JSON)
	                .body(user)
	                .exchange((request, response) -> {
	                	System.out.println(diaChi + "/register");
	                	System.out.println("gọi");
	                 APResponse apResponse = null;
	                    if (response.getBody().available() > 0) { 
	                        
	                        apResponse = objectMapper.readValue(response.getBody(), APResponse.class);
	                    }
	                    return apResponse;
	                });
	}
	
	@Override
	public APResponse loginUser(String username, String password) {
	    try {
	       
	        String url = UriComponentsBuilder.fromUriString(diaChi)
	                .path("/login")
	                .queryParam("username", username)
	                .queryParam("password", password)
	                .toUriString();

	        // Gửi yêu cầu GET đến backend để đăng nhập
	        return restClient.get()
	                .uri(url)
	                .accept(MediaType.APPLICATION_JSON)  // Chỉ định kiểu dữ liệu là JSON
	                .exchange((request, response) -> {
	                    APResponse apResponse = null;
	                    if (response.getBody().available() > 0) {
	                        // Đọc dữ liệu từ phản hồi và chuyển thành đối tượng APResponse
	                        apResponse = objectMapper.readValue(response.getBody(), APResponse.class);
	                    }
	                    return apResponse;
	                });
	    } catch (RestClientException e) {
	        // Xử lý lỗi và trả về thông báo lỗi
	        return new APResponse(500, null, null, "Login failed: " + e.getMessage());
	    }
	}

}
