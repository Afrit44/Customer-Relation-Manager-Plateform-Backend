package com.example.crmbackend.DTO;

import com.example.crmbackend.Model.Admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO extends OfficerDTO  {

//	private Integer Id;

	private String adminPersonalEmail;

	public static AdminDTO fromEntity(Admin admin) {
		
		if (admin == null) {
			//TODO throw an exception
		}
		
		return AdminDTO.builder()
				.adminPersonalEmail(admin.getAdminEmail())
				.departement(admin.getDepartement())
//				.Id(admin.getId())
				.firstName(admin.getFirstName())
				.lastName(admin.getLastName())
//				.dateOB(admin.getDateOB())s
				.adress(admin.getAdress())
				.gender(admin.getGender())
				.password(admin.getPassword())
				.email(admin.getEmail())
				.build();
	}
	
	public static Admin toEntity(AdminDTO adminDto) {
		if (adminDto == null) {
			
			//TODO throw an exception
		}

		Admin admin = new Admin();
//		admin.setId(adminDto.getId());
		admin.setFirstName(adminDto.getFirstName());
		admin.setLastName(adminDto.getLastName());
//		admin.setDateOB(adminDto.getDateOB());
		admin.setAdress(adminDto.getAdress());
		admin.setGender(adminDto.getGender());
		admin.setPassword(adminDto.getPassword());
		admin.setEmail(adminDto.getEmail());
		admin.setDepartement(adminDto.getDepartement());
		admin.setAdminEmail(adminDto.getAdminPersonalEmail());
		return admin;
	}
}
