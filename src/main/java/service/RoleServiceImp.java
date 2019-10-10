package service;

import entity.Role;
import mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("RoleService")
@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleMapper mapper;
    @Override
    public List<Role> findRoleForAdmin(Integer roleid) {
        return mapper.selectRoleForAdmin(roleid);
    }
}
