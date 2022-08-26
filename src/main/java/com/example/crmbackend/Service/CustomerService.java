package com.example.crmbackend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.crmbackend.DTO.UserDTO;
import com.example.crmbackend.Model.Customer;
import com.example.crmbackend.Model.Request;
import com.example.crmbackend.Model.Role;
import com.example.crmbackend.Model.UserG;
import com.example.crmbackend.Repository.RoleRepository;
import com.example.crmbackend.Repository.UserGRepository;
import com.example.crmbackend.Service.EmailSending.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.crmbackend.DTO.CustomerDTO;
import com.example.crmbackend.DTO.RequestDTO;
import com.example.crmbackend.IService.CustomerIService;
import com.example.crmbackend.Repository.CustomerRepository;
import com.example.crmbackend.Repository.RequestRepository;

@Service
public class CustomerService implements CustomerIService{

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
	public CustomerDTO save(CustomerDTO dto) {
		return CustomerDTO.fromEntity(customerRepository.save(CustomerDTO.toEntity(dto)));
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO dto) throws Exception {
		//Check email is unique
		List<String> listEmails = new ArrayList<>();
		List<UserDTO> users = userGRepository.findAll().stream().map(UserDTO::fromEntity).collect(Collectors.toList());
		for (UserDTO user : users){
			listEmails.add(user.getEmail());
		}
		if(listEmails.contains(dto.getEmail())){
			throw new Exception("Email already exists in DataBase");
		}
		//Main part assign the user to it's role as a customer via ID 3
		Customer customer = CustomerDTO.toEntity(dto);
		customer.setUserRole(roleRepository.findById(3).get());
		//Create RequestsMadeByCustomer
		List<Request> RequestsMadeByCustomer = new ArrayList<Request>();
		customer.setRequestsMadeByCustomer(RequestsMadeByCustomer);
		//Hashing the password
		customer.setPassword(passwordEncoder.encode(dto.getPassword()));
		CustomerDTO customerDTO = CustomerDTO.fromEntity(customerRepository.save(customer));

		//Creating a user to replace the customer in the users of role
		//Adding the customer in the list users of Role
		UserG user = customerToUserG(customer);
		Role role = roleRepository.getById(3);
		List<UserG> usersOfRole = role.getUsersOfRole();
		usersOfRole.add(user);
		role.setUsersOfRole(usersOfRole);
		roleRepository.save(role);
		//Send a creation Email
		emailSender.sendUserCreationEmail(user);
		return customerDTO;
	}

	@Override
	public CustomerDTO getCustomer(int id) {
		return CustomerDTO.fromEntity(customerRepository.findById(id).get()); 
		//return CustomerDTO.fromEntity(customerRepository.getById(id)); 
		//SINCE getById is deprecated I did choose to work with findByID
	}
	
	@Override
	public CustomerDTO findCustomerByEmail(String email) {
		return CustomerDTO.fromEntity(customerRepository.findCustomerByEmail(email)); 
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll().stream().map(CustomerDTO::fromEntity).collect(Collectors.toList()); 
	}


	//Making sure the customer has no requests on DB before deleting him.
	@Override
	public void deleteCustomer(int id) throws Exception {
		List<RequestDTO> listPersonalRequests = getAllPersonalRequests(getCustomer(id).getEmail());
		if (!listPersonalRequests.isEmpty()){
			throw new Exception("Mate you can't delete this customer since it already have Requests, delete requests first");
		}
		customerRepository.deleteById(id);
	}

	@Override
	public List<RequestDTO> getAllPersonalRequests(String email) {
		CustomerDTO customerDTO = findCustomerByEmail(email); 
		List<RequestDTO> listRequests = requestRepository.findAll().stream().map(RequestDTO::fromEntity).collect(Collectors.toList());
		for (RequestDTO dto : listRequests) {
			if (!dto.customerEmail.equals(email)) {
				listRequests.remove(dto);
			}
		}
		return listRequests;		
	}

	public UserG customerToUserG(Customer customer){
		return userGRepository.findById(customer.getId()).get();
	}


}
