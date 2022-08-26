package com.example.crmbackend.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.crmbackend.Model.Customer;
import com.example.crmbackend.Model.Officer;
import com.example.crmbackend.Model.Request;
import com.example.crmbackend.Repository.CustomerRepository;
import com.example.crmbackend.Repository.OfficerRepository;
import com.example.crmbackend.Service.EmailSending.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crmbackend.DTO.RequestDTO;
import com.example.crmbackend.IService.RequestIService;
import com.example.crmbackend.Repository.RequestRepository;

@Service
public class RequestService implements RequestIService {

	@Autowired
	public RequestRepository requestRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private OfficerRepository officerRepository;


	@Override
	public RequestDTO save(RequestDTO dto) {
		return RequestDTO.fromEntity(requestRepository.save(RequestDTO.toEntity(dto)));
	}

	@Override
	public RequestDTO createRequest(RequestDTO dto) {
		Request request = RequestDTO.toEntity(dto);
		Customer customer = customerRepository.findCustomerByEmail(dto.getCustomerEmail());
		request.setRelatedCustomer(customer);
		RequestDTO requestDTO = RequestDTO.fromEntity(requestRepository.save(request));
		customer.getRequestsMadeByCustomer().add(request);
		customerRepository.save(customerRepository.findCustomerByEmail(dto.getCustomerEmail()));
		//Send an email to all officers that a new request has been added
		List<Officer> listOfficers = officerRepository.findAll();
		for (Officer officer :listOfficers){
			emailSender.sendNewRequestEmail(officer, request);
		}
		return requestDTO;
	}

	@Override
	public RequestDTO getRequestById(int id) {

		return RequestDTO.fromEntity(requestRepository.findById(id).get());
	}

	@Override
	public RequestDTO getRequestByName(String requestName) {

		return RequestDTO.fromEntity(requestRepository.findRequestByRequestName(requestName));
	}

	@Override
	public List<RequestDTO> findAllRequest() {

		return requestRepository.findAll().stream().map(RequestDTO::fromEntity).collect(Collectors.toList()); 
	}

	@Override
	public void deleteRequest(int requestId) {
		requestRepository.deleteById(requestId);
	}

	@Override
	public RequestDTO updateRequest(int id, String description) {
		Request request = requestRepository.findById(id).get();
		RequestDTO requestDto = RequestDTO.fromEntity(request);
		requestDto.setDescription(description);
		return RequestDTO.fromEntity(requestRepository.save(RequestDTO.toEntity(requestDto)));
	}



}
