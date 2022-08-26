package com.example.crmbackend.IService;

import java.util.List;

import com.example.crmbackend.DTO.AdminDTO;

public interface AdminIService {

	public AdminDTO save(AdminDTO dto);

	public AdminDTO createAdmin(AdminDTO dto) throws Exception;
	
	public AdminDTO getAdmin(Integer id); 
	
	public List<AdminDTO> getAllAdmins(); 

	public void deleteAdmin(Integer id);

	//public void getStats(); 
	
}
