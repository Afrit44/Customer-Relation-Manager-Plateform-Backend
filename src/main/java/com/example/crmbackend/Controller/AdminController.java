package com.example.crmbackend.Controller;

import java.util.List;

import com.example.crmbackend.DTO.*;
import com.example.crmbackend.IService.FeedbackIService;
import com.example.crmbackend.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.crmbackend.IService.AdminIService;
import com.example.crmbackend.IService.CustomerIService;
import com.example.crmbackend.IService.OfficerIService;
import com.example.crmbackend.Model.State;

import javax.mail.MessagingException;


@RestController
@CrossOrigin
@RequestMapping("/api/admin/")
public class AdminController {

	@Autowired
	private OfficerIService officerService;
	@Autowired
    private RoleService roleService;
	@Autowired
	private CustomerIService customerService;
	@Autowired
	private AdminIService adminIService;
	@Autowired
    private FeedbackIService feedbackIService;
	
	@PostMapping("/createOfficer")
    public OfficerDTO createOfficer(@RequestBody OfficerDTO dto) throws Exception {
        //listRole will add the three roles in role table if it is empty
        roleService.listRole();
        return officerService.createOfficer(dto);
    }
    
    @GetMapping("/getOfficer")
	public OfficerDTO findById(@RequestParam int officerId) {
		return officerService.findById(officerId);
	}
    
    @GetMapping("/getAllOfficer")
    public List<OfficerDTO> getAllOfficers(){
        return officerService.getAllOfficers();
    }
    
    //TODO Update Officer
    
    @DeleteMapping("/deleteOfficer")
    public void deleteOfficer(int officerId) {
    	officerService.deleteOfficer(officerId);
	}
    
    @PostMapping("/createCustomer")
    public CustomerDTO createCustomer(CustomerDTO dto) throws Exception {
        //listRole will add the three roles in role table if it is empty
        roleService.listRole();
        return customerService.createCustomer(dto);
    }
    
    @GetMapping("/getCustomer")
	public CustomerDTO findCustomerById(int customerId) {
		return customerService.getCustomer(customerId);
	}
    
    @GetMapping("/getAllCustomers")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    
    //TODO Update Officer
    
    @DeleteMapping("/deleteCustomer")
    public void deleteCustomer(int customerId) throws Exception {
    	customerService.deleteCustomer(customerId);
	}
    
    @GetMapping("/getPinnedRequests")
	public List<RequestDTO> getPinnedRequests() {
    	return officerService.getPinnedRequests();
	}
	
    @GetMapping("/getTreatedRequests")
	public List<RequestDTO> getTreatedRequests() {
    	return officerService.getTreatedRequests(); 
	}

    @GetMapping("/followRequest")
	public State followRequest(int requestId) {
		return officerService.followRequest(requestId);
	}
    
    @PostMapping("/createAdmin")
    public AdminDTO createAdmin(@RequestBody AdminDTO dto) throws Exception {
        //listRole will add the three roles in role table if it is empty
        roleService.listRole();
	    return adminIService.createAdmin(dto);

    }
    
    @GetMapping("/getAdminById")
    public AdminDTO getAdminById(int adminId) {
    	return adminIService.getAdmin(adminId);
    }
    
    @GetMapping("/getAllAdmins")
    public List<AdminDTO> getAllAdmins() throws MessagingException {
    	return adminIService.getAllAdmins();
    }

    @DeleteMapping("/deleteAdmin")
    public void deleteAdmin(int adminId){
        adminIService.deleteAdmin(adminId);
    }

    @GetMapping("/getAllFeedbacks")
    public List<FeedbackDTO> getAllFeedbacks(){ return feedbackIService.getAllFeedbacks(); }
    
}

