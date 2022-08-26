package com.example.crmbackend.Service;

import com.example.crmbackend.Model.Role;
import com.example.crmbackend.Model.UserG;
import com.example.crmbackend.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crmbackend.IService.RoleIService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@Service
@CrossOrigin
public class RoleService implements RoleIService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role){
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles(){
        return (List<Role>) roleRepository.findAll();
    }

    //listRole will add the three roles in role table if it is empty
    @Override
    public void listRole() {
        if (getAllRoles().isEmpty()){

            Role roleAdmin = new Role();
            roleAdmin.setRoleId(1);
            roleAdmin.setRoleName("Admin");
            List<UserG> listOfAdmins = new LinkedList<UserG>();
            roleAdmin.setUsersOfRole(listOfAdmins);
            roleAdmin = save(roleAdmin);

            Role roleOfficer = new Role();
            roleOfficer.setRoleId(2);
            roleOfficer.setRoleName("Officer");
            List<UserG> listOfOfficers = new LinkedList<UserG>();
            roleAdmin.setUsersOfRole(listOfOfficers);
            roleOfficer = save(roleOfficer);

            Role roleCustomer = new Role();
            roleCustomer.setRoleId(3);
            roleCustomer.setRoleName("Customer");
            List<UserG> listOfCustomers = new LinkedList<UserG>();
            roleAdmin.setUsersOfRole(listOfCustomers);
            roleCustomer = save(roleCustomer);
        }
    }

    @Override
    public Role getRoleById(int id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }


}
