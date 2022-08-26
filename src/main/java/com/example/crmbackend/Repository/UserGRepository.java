package com.example.crmbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.crmbackend.Model.UserG;


@Repository
public interface UserGRepository extends JpaRepository<UserG, Integer> {

    UserG findUserGByEmail(String email);
}
