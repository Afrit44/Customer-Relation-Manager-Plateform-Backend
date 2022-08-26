package com.example.crmbackend.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.crmbackend.DTO.RequestDTO;

public class RequestValidator {
	public static List<String> validate(RequestDTO requestDto){
		List<String> errors = new ArrayList<>(); 
		
		if(requestDto==null) {
			errors.add("You're dto is empty, please type the attributes needed"); 
		}

		if(!StringUtils.hasLength(requestDto.getDescription())) {
			errors.add("Please type your request's desrcption");
		}
		if(!StringUtils.hasLength(requestDto.getRequestName())) {
			errors.add("Please type your request's name");
		}
		
		return errors;
	}
}
