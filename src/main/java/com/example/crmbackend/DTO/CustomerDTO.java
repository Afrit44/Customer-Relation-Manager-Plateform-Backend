package com.example.crmbackend.DTO;

import com.example.crmbackend.Model.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO extends UserDTO {

//	private Integer Id;

	private Date dateOfBirth;

	public static CustomerDTO fromEntity(Customer customer) {
			
			if (customer == null) {
			
				//TODO throw an exception
			}
			
			return CustomerDTO.builder()
//					.Id(customer.getId())
					.firstName(customer.getFirstName())
					.lastName(customer.getLastName())
					.dateOfBirth(customer.getDateOfBirth())
					.adress(customer.getAdress())
					.gender(customer.getGender())
					.password(customer.getPassword())
					.email(customer.getEmail())
					.build();
	
	}
	
	public static Customer toEntity(CustomerDTO customerDto) {
		if (customerDto == null) {
			
			//TODO throw an exception
		}

		Customer customer = new Customer();
//		customer.setId(customerDto.getId());
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setDateOfBirth(customerDto.getDateOfBirth());
		customer.setAdress(customerDto.getAdress());
		customer.setGender(customerDto.getGender());
		customer.setPassword(customerDto.getPassword());
		customer.setEmail(customerDto.getEmail());

		return customer;
	}
}
