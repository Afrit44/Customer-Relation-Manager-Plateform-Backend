package com.example.crmbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.crmbackend.Model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
