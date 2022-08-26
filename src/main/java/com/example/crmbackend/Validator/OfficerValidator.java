package com.example.crmbackend.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.crmbackend.DTO.OfficerDTO;

public class OfficerValidator {
	public static List<String> validate(OfficerDTO officerDto){
		List<String> errors = new ArrayList<>(); 
		
		if(officerDto==null) {
			errors.add("You're dto is empty, please type the attributes needed"); 
		}
		
		if(!StringUtils.hasLength(officerDto.getDepartement())) {
			errors.add("Please type your departement");
		}
		
		return errors;
	}
}

