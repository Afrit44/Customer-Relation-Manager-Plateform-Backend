package com.example.crmbackend.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.crmbackend.DTO.UserDTO;
import com.example.crmbackend.Model.Admin;
import com.example.crmbackend.Model.Role;
import com.example.crmbackend.Model.UserG;
import com.example.crmbackend.Repository.RoleRepository;
import com.example.crmbackend.Repository.UserGRepository;
import com.example.crmbackend.Service.EmailSending.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.crmbackend.DTO.AdminDTO;
import com.example.crmbackend.IService.AdminIService;
import com.example.crmbackend.Repository.AdminRepository;

@Service
public class AdminService implements AdminIService{
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private UserGRepository userGRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EmailSender emailSender;

	@Override
	public AdminDTO save(AdminDTO dto) {
		return AdminDTO.fromEntity(adminRepository.save(AdminDTO.toEntity(dto)));
	}

	@Override
	public AdminDTO createAdmin(AdminDTO dto) throws Exception {
		//Check email is unique
		List<String> listEmails = new ArrayList<>();
		List<UserDTO> users = userGRepository.findAll().stream().map(UserDTO::fromEntity).collect(Collectors.toList());
		for (UserDTO user : users){
			listEmails.add(user.getEmail());
		}
		if(listEmails.contains(dto.getEmail())){
			throw new Exception("Email already exists in DataBase");
		}

		//Main part of the create function
		//Adding role admin to the user.
		Admin admin = AdminDTO.toEntity(dto);
		admin.setUserRole(roleRepository.findById(1).get());
		//Hashing the password
		admin.setPassword(passwordEncoder.encode(dto.getPassword()));
		//Save the admin
		AdminDTO adminDTO = AdminDTO.fromEntity(adminRepository.save(admin));
		//Creating a user to replace the admin in the users of role
		//Adding the admin in the list users of Role
		UserG user = adminToUser(admin);
		Role role = roleRepository.getById(1);
		List<UserG> usersOfRole = role.getUsersOfRole();
		usersOfRole.add(user);
		role.setUsersOfRole(usersOfRole);
		roleRepository.save(role);
		//Send a creation Email
		emailSender.sendUserCreationEmail(user);
		return adminDTO;
	}

	@Override
	public AdminDTO getAdmin(Integer id) {
		if (id == null) {
			//log.error(" ID is null");
		}

		return AdminDTO.fromEntity(adminRepository.findById(id).get()); 
		
		//TODO after fixing errors and adding Exception in the return.
		//return admin.orElseThrow(() ->new EntityNotFoundException("Admin",ErrorCodes.ADMIN_NOT_FOUND));
	}

	@Override
	public List<AdminDTO> getAllAdmins() {
		return adminRepository.findAll().stream().map(AdminDTO::fromEntity).collect(Collectors.toList()); 
//		List<AdminDTO> listAdminsDTO = new ArrayList<AdminDTO>(); 
//		List<Admin> listAdmins = adminRepository.findAll(); 
//		for (Admin admin : listAdmins) {
//			AdminDTO adminDTO = AdminDTO.fromEntity(admin);
//			listAdminsDTO.add(adminDTO);
//		}
//		return listAdminsDTO;
	}

	@Override
	public void deleteAdmin(Integer id){
		adminRepository.deleteById(id);
	}

	public UserG adminToUser(Admin admin){
		return userGRepository.findById(admin.getId()).get();
	}

}
