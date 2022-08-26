package com.example.crmbackend.IService;

import java.util.List;

import com.example.crmbackend.DTO.RequestDTO;
import com.example.crmbackend.Model.Request;

public interface RequestIService {
	
	public RequestDTO save(RequestDTO dto);

	public RequestDTO createRequest(RequestDTO dto);
	
	public RequestDTO getRequestById(int id); 
	
	public List<RequestDTO> findAllRequest();

	public RequestDTO getRequestByName(String name);

	public void deleteRequest(int requestId);

	public RequestDTO updateRequest (int id, String description);

}