package com.example.crmbackend.Model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="Officer")
public class Officer extends UserG {

	@Column(name="Departement")
	public String departement;

	@OneToMany
	private List<Request> RequestsTreatedByOfficer;
}
