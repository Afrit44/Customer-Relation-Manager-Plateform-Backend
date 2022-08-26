package com.example.crmbackend.Model;

import java.sql.Date;

import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Request")
public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer requestId; 
	
	@Column(name="CustomerEmail")
	public String customerEmail;
	
	@Enumerated(EnumType.STRING)
	@Column(name="CurrentState")
	private State currentState; 
	
	@Column(name="description")
	private String description;
	
	@Column(name="requestName")
	private String requestName;
	
	@Column(name = "photo")
	private String photo;

//	@CreatedDate
//	@Column(name="CreationDate")
//	@JsonIgnore
//	private Date creationDate;
//
//	@LastModifiedDate
//	@Column(name="lastModifiedDate")
//	@JsonIgnore
//	private Date lastModifiedDate;

	@ManyToOne
	private Customer relatedCustomer;
	
	@ManyToOne
	private Officer relatedOfficer;

	@OneToOne
	private Feedback relatedFeedback;
		
	
}
