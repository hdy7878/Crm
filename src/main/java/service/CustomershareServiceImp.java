package service;

import entity.Customershare;
import mapper.CustomershareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("CustomershareService")
@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
public class CustomershareServiceImp implements CustomershareService {
    @Autowired
    private CustomershareMapper mapper;
    @Override
    public List<Customershare> findAllCustomershare() {
        return mapper.selectCustomershare();
    }

    @Override
    public int subCustomershare(Integer id) {
        return mapper.subCustomershare(id);
    }

    @Override
    public int updateCustomershare(Customershare customershare) {
        return mapper.updateCustomershare(customershare);
    }

    @Override
    public int insertCustomershare(Customershare customershare) {
        return mapper.insertCustomershare(customershare);
    }

    @Override
    public Customershare findCustomershareById(Integer id) {
        return mapper.selectCustomershareById(id);
    }

    @Override
    public List<Customershare> findCustomershareByEmpId(Integer empid) {
        return mapper.selectCustomershareByEmpId(empid);
    }

	@Override
	public Customershare findCustomershareByTerm(Integer empid, Integer cid) {
		return mapper.findCustomershareByTerm(empid,cid);
	}

    @Override
    public List<Customershare> findAllCustomershareBycusname(String cusname) {
        return mapper.findAllCustomershareBycusname(cusname);
    }

    @Override
    public List<Customershare> findAllCustomershareByIdcusname(Integer empid, String cusname) {
        return mapper.findAllCustomershareByIdcusname(empid, cusname);
    }
}
