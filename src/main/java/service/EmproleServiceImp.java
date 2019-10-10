package service;

import entity.Emprole;
import mapper.EmproleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("EmproleService")
@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
public class EmproleServiceImp implements EmproleService {
    @Autowired
    private EmproleMapper mapper;
    @Override
    public int addEmprole(Emprole emprole) {
        return mapper.insertEmprole(emprole);
    }

    @Override
    public int subEmprole(Integer empid) {
        return mapper.subEmprole(empid);
    }

    @Override
    public int updateEmprole(Emprole emprole) {
        return mapper.updateEmprole(emprole);
    }

    @Override
    public List<Emprole> findAllEmprole() {
        return mapper.selectAllEmprole();
    }

    @Override
    public Emprole findRoleid(Integer empid) {
        return mapper.selectRoleid(empid);
    }

    @Override
    public Emprole findEmproleById(Integer id) {
        return mapper.selectEmproleById(id);
    }

    @Override
    public List<Emprole> findEmproleForAdmin(Integer roleid) {
        return mapper.selectEmproleForAdmin(roleid);
    }
}
