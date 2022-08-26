package com.example.crmbackend.Validator;

import com.example.crmbackend.DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

public class UserValidator {
	public static List<String> validate(UserDTO userDto){
		List<String> errors = new ArrayList<>(); 
		
		if(userDto==null) {
			errors.add("You're dto is empty, please type the attributes needed"); 
		}

		if(!StringUtils.hasLength(userDto.getFirstName())) {
			errors.add("Please type your first name");
		}
		if(!StringUtils.hasLength(userDto.getLastName())) {
			errors.add("Please type your last name");
		}
//		if(userDto.getDateOB()==null) {
//			errors.add("Please type your date of Birth");
//		}
		if(userDto.getGender()==null) {
			errors.add("Please select your gender");
		}
		if(!StringUtils.hasLength(userDto.getPassword())) {
			errors.add("Please type your password");
		}
		if(!StringUtils.hasLength(userDto.getEmail())) {
			errors.add("Please type your Email");
		}
		
		
		return errors;
	}
}