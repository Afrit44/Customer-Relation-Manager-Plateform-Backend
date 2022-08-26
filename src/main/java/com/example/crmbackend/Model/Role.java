package com.example.crmbackend.Model;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Role")
public class Role {

	@Id
	@Column(name="roleId")
	private Integer roleId;
	
	@Column(name="roleName")
	private String roleName;

	@OneToMany
	private List<UserG> usersOfRole;
}
