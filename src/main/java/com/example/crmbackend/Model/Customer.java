package com.example.crmbackend.Model;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="Customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends UserG {

	private Date dateOfBirth;

	@OneToMany
	private List<Request> RequestsMadeByCustomer;

	@OneToOne
	private Feedback feedbackGiven;
}
