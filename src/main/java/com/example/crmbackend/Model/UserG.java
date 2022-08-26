package com.example.crmbackend.Model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name="UserG")
public class UserG {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer Id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="adress")
	private String adress;
	
	@Column(name="gender")
	private Gender gender;
	
//	@CreatedDate
//	@Column(name="CreationDate", nullable=false)
//	@JsonIgnore
//	private Date creationDate;
	
//	@LastModifiedDate
//	@Column(name="lastModifiedDate")
//	@JsonIgnore
//	private Date lastModifiedDate;


	//Each UserG has only one Role
	@ManyToOne
	private Role UserRole;


}
