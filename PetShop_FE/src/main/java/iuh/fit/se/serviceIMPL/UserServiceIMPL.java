package iuh.fit.se.serviceIMPL;

import java.util.LinkedHashMap;
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
	public APResponse loginUser(User user) {
	    try {
	       
	
	        return restClient.post()
	                .uri(diaChi + "/login")  
	                .accept(MediaType.APPLICATION_JSON)
	                .body(user)
	                .exchange((request, response) -> {
	                    APResponse apResponse = null;
	                    if (response.getBody().available() > 0) {
	                      
	                        apResponse = objectMapper.readValue(response.getBody(), APResponse.class);
	                        if (apResponse.getData() instanceof LinkedHashMap) {
	                          
	                            User loggedUser = objectMapper.convertValue(
	                                    apResponse.getData(),
	                                    new TypeReference<User>() {}
	                            );

	                          
	                            apResponse.setData(loggedUser);
	                        }
	                    }
	                    return apResponse;
	                });
	    } catch (RestClientException e) {
	     
	        return new APResponse(500, null, null, "Login failed: " + e.getMessage());
	    }
	}
	
	 @Override
	    public APResponse updateUser(Long userId, User user) {
	        try {
	            
	            return restClient.put()
	                    .uri(diaChi + "/updateUser/{userId}",userId)  
	                    .accept(MediaType.APPLICATION_JSON)
	                    .body(user) 
	                    .exchange((request, response) -> {
	                        APResponse apResponse = null;
	                        if (response.getBody().available() > 0) {
	                           
	                            apResponse = objectMapper.readValue(response.getBody(), APResponse.class);
	                        }
	                        return apResponse;
	                    });
	        } catch (RestClientException e) {
	            
	            return new APResponse(500, null, null, "Update failed: " + e.getMessage());
	        }
	    }

	 @Override
		public APResponse changePasswordUser(long id, String oldPassword, String newPassword, String confirmPassword) {
			try {
				// Tạo payload để gửi yêu cầu
				LinkedHashMap<String, Object> payload = new LinkedHashMap<>();
				payload.put("id", id);
				payload.put("oldPassword", oldPassword);
				payload.put("newPassword", newPassword);
				payload.put("confirmPassword", confirmPassword);

				// Gửi yêu cầu tới API backend
				return restClient.post().uri(diaChi + "/change-password").accept(MediaType.APPLICATION_JSON).body(payload)
						.exchange((request, response) -> {
							APResponse apResponse = null;
							if (response.getBody().available() > 0) {
								apResponse = objectMapper.readValue(response.getBody(), APResponse.class);
							}
							return apResponse;
						});
			} catch (RestClientException e) {
				return new APResponse(500, null, null, "Change password failed: " + e.getMessage());
			} catch (Exception e) {
				return new APResponse(500, null, null, "An error occurred: " + e.getMessage());
			}
		}
}
