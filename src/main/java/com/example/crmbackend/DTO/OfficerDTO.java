package com.example.crmbackend.DTO;

import com.example.crmbackend.Model.Officer;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficerDTO extends UserDTO {

//	private Integer id;

	private String departement;
	
	public static OfficerDTO fromEntity(Officer officer) {
		
		if (officer == null) {
		
			//TODO throw an exception
		}
		
		return OfficerDTO.builder()
				.departement(officer.getDepartement())
//				.Id(officer.getId())
				.firstName(officer.getFirstName())
				.lastName(officer.getLastName())
//				.dateOB(officer.getDateOB())
				.adress(officer.getAdress())
				.gender(officer.getGender())
				.password(officer.getPassword())
				.email(officer.getEmail())
				.build();

	}

	public static Officer toEntity(OfficerDTO officerDto) {
		if (officerDto == null) {
			
			//TODO throw an exception
		}
		
		Officer officer = new Officer();
//		officer.setId(officerDto.getId());
		officer.setFirstName(officerDto.getFirstName());
		officer.setLastName(officerDto.getLastName());
//		officer.setDateOB(officerDto.getDateOB());
		officer.setAdress(officerDto.getAdress());
		officer.setGender(officerDto.getGender());
		officer.setPassword(officerDto.getPassword());
		officer.setEmail(officerDto.getEmail());
		officer.setDepartement(officerDto.getDepartement());
		return officer; 
	}
}
