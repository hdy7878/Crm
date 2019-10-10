package service;

import entity.Customervisit;
import mapper.CustomervisitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("CustomervisitService")
@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
public class CustomervisitServiceImp implements CustomervisitService {
    @Autowired
   private CustomervisitMapper mapper;

    @Override
    public List<Customervisit> findAllCustomervisit() {
        return mapper.selectAllCustomervisit();
    }

    @Override
    public int subCustomervisit(Integer id) {
        return mapper.subCustomervisit(id);
    }

    @Override
    public int updateCustomervisit(Customervisit customervisit) {
        return mapper.updateCustomervisit(customervisit);
    }

    @Override
    public Customervisit findCustomervisit(Integer id) {
        return mapper.selectCustomervisit(id);
    }

    @Override
    public int insertCustomervisit(Customervisit customervisit) {
        return mapper.insertCustomervisit(customervisit);
    }

    @Override
    public List<Customervisit> findCustomervisitById(Integer empid) {
        return mapper.selectCustomervisitById(empid);
    }

    @Override
    public List<Customervisit> findAllCustomervisitBycusname(String cusname) {
        return mapper.findAllCustomervisitBycusname(cusname);
    }

    @Override
    public List<Customervisit> findAllCustomervisitByIdcusname(Integer empid, String cusname) {
        return mapper.findAllCustomervisitByIdcusname(empid,cusname);
    }
}
