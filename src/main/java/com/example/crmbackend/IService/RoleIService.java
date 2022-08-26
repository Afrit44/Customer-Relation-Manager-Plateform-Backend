package com.example.crmbackend.IService;

import com.example.crmbackend.Model.Role;

import java.util.List;

public interface RoleIService {

    public Role save(Role role);

    public void listRole();

    public Role getRoleById(int id);

    public List<Role> getAllRoles();

    public void deleteRole(int id);

}
