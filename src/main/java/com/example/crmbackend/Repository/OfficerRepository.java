package com.example.crmbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crmbackend.Model.Officer;


@Repository
public interface OfficerRepository extends JpaRepository<Officer, Integer> {
    public Officer getOfficerByEmail(String officerEmail);
}
