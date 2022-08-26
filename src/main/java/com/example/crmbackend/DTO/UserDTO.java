package com.example.crmbackend.DTO;

import com.example.crmbackend.Model.Gender;
import com.example.crmbackend.Model.UserG;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

//	private Integer Id;

	private String firstName;
	
	private String lastName;
	
//	private Date dateOB;
	
	private String adress;
	
	private Gender gender;	
	
	private String email;
	
	private String password;
	
//	private RoleDTO UserRole;

//	private List<RequestDTO> RequestsByUsers;
	
	
	
	public static UserDTO fromEntity(UserG userG) {
		
		if (userG == null) {
			//TODO throw an exception
		}
		
		return UserDTO.builder()
//				.Id(userG.getId())
				.firstName(userG.getFirstName())
				.lastName(userG.getLastName())
//				.dateOB(userG.getDateOB())
				.adress(userG.getAdress())
				.gender(userG.getGender())
				.password(userG.getPassword())
				.email(userG.getEmail())
				.build();
	}
	
	public static UserG toEntity(UserDTO userDto) {
		if (userDto == null) {
			//TODO throw an exception
		}
		UserG userG = new UserG();
//		userG.setId(userDto.getId());
		userG.setFirstName(userDto.getFirstName());
		userG.setLastName(userDto.getLastName());
//		userG.setDateOB(userDto.getDateOB());
		userG.setAdress(userDto.getAdress());
		userG.setGender(userDto.getGender());
		userG.setPassword(userDto.getPassword());
		userG.setEmail(userDto.getEmail());
		
		return userG;
	}
}
