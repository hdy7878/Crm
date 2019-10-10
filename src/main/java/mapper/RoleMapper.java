package mapper;

import entity.Role;

import java.util.List;

public interface RoleMapper {
    public List<Role> selectRoleForAdmin(Integer roleid);
}
