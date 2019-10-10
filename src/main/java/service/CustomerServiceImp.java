package service;

import entity.Customer;
import entity.Customershare;
import entity.Customervisit;
import mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CustomerService")
@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
public class CustomerServiceImp implements CustomerService{
    @Autowired
    private CustomerMapper mapper;

    @Override
    public int insertCustomer(Customer customer) {
        return mapper.insertCustomer(customer);
    }



    @Override
    public int subCustomer(Integer cid) {
        return mapper.subCustomer(cid);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return mapper.updateCustomer(customer);
    }

    @Override
    public List<Customer> findAllCustomer1() {
        return mapper.selectAllCustomer1();
    }
    @Override
    public List<Customer> findAllCustomer() {
        return mapper.selectAllCustomer();
    }
    @Override
    public Customer findCustomerById(Integer cid) {
        return mapper.selectCustomerById(cid);
    }

    @Override
    public List<Customer> findCusForEmp(Integer empid) {
        return mapper.selectCusForEmp(empid);
    }

    public List<Customershare> findCusshareByCid(int cid) {
        return mapper.findCusshareByCid(cid);
    }


    public List<Customervisit> findCusvisitByCid(int cid) {
        return mapper.findCusvisitByCid(cid);
    }

    @Override
    public List<Customer> findAllCustomerByCusname(String cusname) {
        return mapper.findAllCustomerByCusname(cusname);
    }

    @Override
    public List<Customer> findCusForEmpCusname(Integer empid, String cusname) {
        return mapper.findCusForEmpCusname(empid, cusname);


    }
}
