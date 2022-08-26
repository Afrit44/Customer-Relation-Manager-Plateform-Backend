package com.example.crmbackend.IService;

import java.util.List;

import com.example.crmbackend.DTO.OfficerDTO;
import com.example.crmbackend.DTO.RequestDTO;
import com.example.crmbackend.Model.State;


public interface OfficerIService {

	public OfficerDTO save(OfficerDTO dto);

	public OfficerDTO createOfficer(OfficerDTO dto) throws Exception;
	
	public OfficerDTO findById(int id); 
	
	public List<OfficerDTO> getAllOfficers(); 
	
	//TODO update officer 
	
	public void deleteOfficer(int id);
	
	public RequestDTO changeRequestState(int requestId, int officerId, State newState);
	
	public List<RequestDTO> getPinnedRequests();
	
	public List<RequestDTO> getTreatedRequests();
	
	public State followRequest(int requestId);


	
}
