package service;

import entity.Role;

import java.util.List;

public interface RoleService {
   public List<Role> findRoleForAdmin(Integer roleid);
}
