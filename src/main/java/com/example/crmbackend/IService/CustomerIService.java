package com.example.crmbackend.IService;

import java.util.List;

import com.example.crmbackend.DTO.CustomerDTO;
import com.example.crmbackend.DTO.RequestDTO;

public interface CustomerIService {

	public CustomerDTO save(CustomerDTO dto);

	public CustomerDTO createCustomer(CustomerDTO dto) throws Exception;
	
	public CustomerDTO getCustomer(int id);
	
	public CustomerDTO findCustomerByEmail(String email); 
	
	public List<CustomerDTO> getAllCustomers();
		
	public void deleteCustomer (int id) throws Exception;
	
	public List<RequestDTO> getAllPersonalRequests(String email); 
	
	//public void createFeedback(); 
	
	//public void rateService(); 
	
	//public void validateRequest(); 
	
}
