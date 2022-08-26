package com.example.crmbackend.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.crmbackend.DTO.AdminDTO;
import com.example.crmbackend.DTO.UserDTO;
import com.example.crmbackend.IService.UserIService;
import com.example.crmbackend.Model.*;
import com.example.crmbackend.Repository.*;
import com.example.crmbackend.Service.EmailSending.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.crmbackend.DTO.OfficerDTO;
import com.example.crmbackend.DTO.RequestDTO;
import com.example.crmbackend.IService.OfficerIService;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Service
public class OfficerService implements OfficerIService{

	@Autowired
	public OfficerRepository officerRepository;
	@Autowired
	public AdminRepository adminRepository;
	@Autowired 
	public CustomerRepository customerRepository;
	@Autowired 
	public RequestRepository requestRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserGRepository userGRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EmailSender emailSender;

	@Override
	public OfficerDTO save(OfficerDTO dto){
		return OfficerDTO.fromEntity(officerRepository.save(OfficerDTO.toEntity(dto))); 
	}

	@Override
	public OfficerDTO createOfficer(OfficerDTO dto) throws Exception {
		//Check email is unique
		List<String> listEmails = new ArrayList<>();
		List<UserDTO> users = userGRepository.findAll().stream().map(UserDTO::fromEntity).collect(Collectors.toList());
		for (UserDTO user : users){
			listEmails.add(user.getEmail());
		}
		if(listEmails.contains(dto.getEmail())){
			throw new Exception("Email already exists in DataBase");
		}
		//Main part
		Officer officer = OfficerDTO.toEntity(dto);
		//Set Role of the User to Officer
		officer.setUserRole(roleRepository.findById(2).get());
		//Create empty list for the request that have been treated by an Officer.
		List<Request> listRequestsTreatedByOfficer = new ArrayList<Request>();
		officer.setRequestsTreatedByOfficer(listRequestsTreatedByOfficer);
		//Hashing the password
		officer.setPassword(passwordEncoder.encode(dto.getPassword()));
		//We have to create a first request befor creating officer get the first request's Id created and add it here
		OfficerDTO officerDTO = OfficerDTO.fromEntity(officerRepository.save(officer));
		//Creating a user to replace the officer in the users of role
		//Adding the officer in the list users of Role
		UserG user = officerToUser(officer);
		Role role = roleRepository.getById(2);
		List<UserG> usersOfRole = role.getUsersOfRole();
		usersOfRole.add(user);
		role.setUsersOfRole(usersOfRole);
		roleRepository.save(role);
		//Send a creation Email
		emailSender.sendUserCreationEmail(user);
		return officerDTO;
	}

	@Override
	public OfficerDTO findById(int id) {
		
		return OfficerDTO.fromEntity(officerRepository.findById(id).get()); 
	}

	@Override
	public List<OfficerDTO> getAllOfficers() {
		List<Officer> listAll =  officerRepository.findAll();
		List<Admin> listAdmin = adminRepository.findAll();
		List<Officer> listOfficer = new ArrayList<Officer>();
		List<Integer> listAdminId = new ArrayList<Integer>();
		for (Admin j : listAdmin){
			listAdminId.add(j.getId());
		}
		for (Officer i : listAll){
			if (!listAdminId.contains(i.getId())){
				listOfficer.add(i);
			}
		}
		return listOfficer.stream().map(AdminDTO::fromEntity).collect(Collectors.toList());
	}
	
    //TODO Update Officer

	@Override
	public void deleteOfficer(int id) {
		officerRepository.deleteById(id);
	}
	
	@Override
	public RequestDTO changeRequestState(int requestId, int officerId, State newState) {
		Officer officer = officerRepository.findById(officerId).get();
		Request request = requestRepository.findById(requestId).get();
		//Set treated requests by officer List
		if (newState.equals(State.INEXECUTION)) {
			List<Request> list = officer.getRequestsTreatedByOfficer();
			list.add(request);
			officer.setRequestsTreatedByOfficer(list);
			officerRepository.save(officer);
		}
		//Set the state
		request.setCurrentState(newState);
		request.setRelatedOfficer(officer);
		requestRepository.save(request);
		emailSender.sendNotificationToCustomer(customerRepository.findCustomerByEmail(request.customerEmail), request);
		return RequestDTO.fromEntity(request);
	}

	@Override
	public List<RequestDTO> getPinnedRequests() {
		List<Request> listOfRequests = requestRepository.findAll();
		System.out.println(listOfRequests);
		List<Request> pinnedRequests = new ArrayList<Request>(); 
		for(Request request : listOfRequests) {
			System.out.println("IN +++"+request);
			if(request.getCurrentState().toString().equals("PINNED")){
				pinnedRequests.add(request); 
			}
		}
		System.out.println("final ="+pinnedRequests);
		//Mapping from Request entities to RequestDTO
		return pinnedRequests.stream().map(RequestDTO::fromEntity).collect(Collectors.toList());
	}
	
	@Override
	public List<RequestDTO> getTreatedRequests() {
		List<Request> listOfRequests = requestRepository.findAll();
		List<Request> treatedRequests = new ArrayList<Request>(); 
		for(Request request : listOfRequests) {
			if(request.getCurrentState().toString().equals("VALIDATED")){
				treatedRequests.add(request); 
			}
		}
		//Mapping from Request entities to RequestDTO
		return treatedRequests.stream().map(RequestDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public State followRequest(int requestId) {
		RequestDTO requestDTO = RequestDTO.fromEntity(requestRepository.findById(requestId).get());
		return requestDTO.getCurrentState();
	}

	public UserG officerToUser(Officer officer){
		return userGRepository.findById(officer.getId()).get();
	}


}
