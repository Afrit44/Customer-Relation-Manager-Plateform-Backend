package com.example.crmbackend.Controller;

import java.util.List;

import com.example.crmbackend.DTO.FeedbackDTO;
import com.example.crmbackend.IService.FeedbackIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crmbackend.DTO.RequestDTO;
import com.example.crmbackend.IService.CustomerIService;
import com.example.crmbackend.IService.RequestIService;


@RestController
@CrossOrigin
@RequestMapping("/api/customer/")
public class CustomerController {

	@Autowired
	private CustomerIService customerService;
	@Autowired
	private RequestIService requestService;
	@Autowired
	private FeedbackIService feedbackIService;
	
	@PostMapping("/createRequest")
	public RequestDTO createRequest(RequestDTO dto) {
		return requestService.createRequest(dto);
	}
	
	@GetMapping("/getRequestbyId")
	public RequestDTO getRequestById(int requestId) {
		return requestService.getRequestById(requestId);
	}

	@GetMapping("/getRequestByName")
	public RequestDTO getRequestByName(String requestName) {
		return requestService.getRequestByName(requestName);
	}

	@GetMapping("/getAllRequest")
	public List<RequestDTO> findAllRequest() {
		return requestService.findAllRequest(); 
	}
	
	@PutMapping("/updateRequest")
	public RequestDTO updateRequest(int requestId, String description) {
		return requestService.updateRequest(requestId, description);
	}

	@DeleteMapping("/deleteRequest")
	public void deleteRequest(int requestId) {
		requestService.deleteRequest(requestId);
	}
	
	@GetMapping("/getAllPersonalRequests")
	public List<RequestDTO> getAllPersonalRequests(String customerEmail){
		return customerService.getAllPersonalRequests(customerEmail);
	}

	@PostMapping("/createFeedback")
	public FeedbackDTO createFeedback(int customerId, int requestId, FeedbackDTO feedbackDTO){ return feedbackIService.createFeedback( customerId, requestId, feedbackDTO); }

	@GetMapping("/getAllFeedbacksByCustomer")
	public List<FeedbackDTO> getAllFeedbacksByCustomer(String customerEmail){ return feedbackIService.getAllFeedbacksByCustomer(customerEmail); }

	@GetMapping("/getFeedbackById")
	public FeedbackDTO getFeedbackById(int feedbackId){ return feedbackIService.getFeedbackById(feedbackId); }

	@PutMapping("/updateFeedbackDescription")
	public FeedbackDTO updateFeedbackDescription(int feedbackId, String feedbackDescription){ return feedbackIService.updateFeedbackDescription(feedbackId, feedbackDescription); }

	@PutMapping("/updateFeedbackRating")
	public FeedbackDTO updateFeedbackRating(int feedbackId, int rating){ return feedbackIService.updateFeedbackRating(feedbackId, rating); }

	@DeleteMapping("/deleteFeedback")
	public void deleteFeedback(int feedbackId){  feedbackIService.deleteFeedback(feedbackId); }
    
}
