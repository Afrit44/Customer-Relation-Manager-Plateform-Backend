package com.example.crmbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crmbackend.Model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	  Customer findCustomerByEmail(String email);

}

