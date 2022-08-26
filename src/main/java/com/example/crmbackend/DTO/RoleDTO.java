package com.example.crmbackend.DTO;

import java.util.List;

import com.example.crmbackend.Model.Role;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleDTO {
	
	private Integer roleId; 
	
	private String roleName;
	
	private List<UserDTO> usersOfRole;
	
	public static RoleDTO fromEntity(Role role) {
		
		if (role == null) {
		
			//TODO throw an exception
		}
		
		return RoleDTO.builder()
				.roleId(role.getRoleId())
				.roleName(role.getRoleName())
				.build();

	}
	
	public static Role toEntity(RoleDTO roleDTO) {
		if (roleDTO == null) {
			
			//TODO throw an exception
		}
		
		Role role = new Role(); 
		role.setRoleId(
				roleDTO.getRoleId());
		role.setRoleName(roleDTO.getRoleName());
		return role; 
	}
}
