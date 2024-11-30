package iuh.fit.se.serviceIMPL;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import iuh.fit.se.entities.Order;
import iuh.fit.se.services.OrderService;
import iuh.fit.se.utils.APResponse;

@Service
public class OrderServiceIMPL implements OrderService {
	
	private RestClient restClient;
	private ObjectMapper objectMapper;
	private static final String diaChi = "http://localhost:9998/api";

	  public OrderServiceIMPL(RestClient restClient, ObjectMapper objectMapper) {
	        this.restClient = restClient;
	        this.objectMapper = objectMapper;
	    }
	  @Override
	    public APResponse getOrderHistory(Long userId) {
		  System.out.println(diaChi + "/orderhistory/{userId}" + userId);
	        try {
	        
	            return restClient.get()
	                    .uri(diaChi + "/orderhistory/{userId}", userId)
	                    .accept(MediaType.APPLICATION_JSON)
	                    .exchange((request, response) -> {
	                        APResponse apResponse = null;
	                        if (response.getBody().available() > 0) {
	                           
	                            apResponse = objectMapper.readValue(response.getBody(), APResponse.class);

	                            if (apResponse.getData() instanceof LinkedHashMap) {
	                                
	                                List<Order> orderHistory = objectMapper.convertValue(
	                                        apResponse.getData(),
	                                        new TypeReference<List<Order>>() {}
	                                );

	                          
	                                apResponse.setData(orderHistory);
	                            }
	                        }
	                        return apResponse;
	                    });
	        } catch (RestClientException e) {
	           
	            return new APResponse(500, null, null, "Failed to fetch order history: " + e.getMessage());
	        }
	    }
}
