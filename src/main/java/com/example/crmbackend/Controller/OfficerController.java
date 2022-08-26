package com.example.crmbackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crmbackend.DTO.CustomerDTO;
import com.example.crmbackend.DTO.RequestDTO;
import com.example.crmbackend.Model.State;
import com.example.crmbackend.IService.CustomerIService;
import com.example.crmbackend.IService.OfficerIService;
import com.example.crmbackend.IService.RequestIService;

@RestController
@CrossOrigin
@RequestMapping("/api/officer/")
public class OfficerController {
	
	@Autowired
	public CustomerIService customerService;
	@Autowired 
	public RequestIService requestService; 
	@Autowired 
	public OfficerIService officerService; 
	
	@GetMapping("/getAllCustomers")
	public List<CustomerDTO> getAllCustomers() {
		return customerService.getAllCustomers(); 
	}
	
	@GetMapping("/getPinnedRequests")
	public List<RequestDTO> getPinnedRequests() {
		return officerService.getPinnedRequests(); 
	}
	
	@GetMapping("/followRequest")
	public State followRequest(int requestId) {
		return officerService.followRequest(requestId); 
	}
	
	@PutMapping("/changeRequestState")
	public RequestDTO changeRequestState(int requestId, State newState, int officerId) {
		return officerService.changeRequestState(requestId, officerId, newState);
	}
}
