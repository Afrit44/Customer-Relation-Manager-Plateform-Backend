package com.example.crmbackend.DTO;

import java.sql.Date;

import com.example.crmbackend.Model.Request;
import com.example.crmbackend.Model.State;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Builder
@Data
public class RequestDTO {

	
	public String customerEmail;
	
	private State currentState; 
	
	private String description;
	
	private String requestName;
	
//	private String photo;

//	private Date creationDate;

//	private Date lastModifiedDate;

	public static RequestDTO fromEntity(Request request) {
		
		if (request == null) {
			//TODO throw an exception
		}
		
		return RequestDTO.builder()
				.customerEmail(request.getCustomerEmail())
				.currentState(request.getCurrentState())
				.description(request.getDescription())
				.requestName(request.getRequestName())
//				.photo(request.getPhoto())
				.build();

	}

	public static  Request toEntity(RequestDTO requestDTO) {
		if (requestDTO == null) {
			//TODO throw an exception
		}
		
		Request request = new Request(); 
		request.setCustomerEmail(requestDTO.getCustomerEmail());
		request.setCurrentState(requestDTO.getCurrentState());
		request.setDescription(requestDTO.getDescription());
		request.setRequestName(requestDTO.getRequestName());
//		request.setPhoto(requestDTO.getPhoto());
		return request;

	}
}
