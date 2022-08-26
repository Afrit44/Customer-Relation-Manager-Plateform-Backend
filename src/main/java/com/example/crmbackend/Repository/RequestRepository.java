package com.example.crmbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crmbackend.Model.Request;


@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    public Request findRequestByRequestName(String requestName);
}
