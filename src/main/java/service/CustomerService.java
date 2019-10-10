package service;

import java.util.List;

import entity.Customer;
import entity.Customershare;
import entity.Customervisit;

public interface CustomerService {
    public int insertCustomer(Customer customer);
    public List<Customer> findAllCustomer();
    public List<Customer> findAllCustomer1();
    public int subCustomer(Integer cid);
    public int updateCustomer(Customer customer);
    public Customer findCustomerById(Integer cid);
    public List<Customer> findCusForEmp(Integer empid);
    
    public List<Customershare> findCusshareByCid(int cid);

	public List<Customervisit> findCusvisitByCid(int cid);

    public List<Customer> findAllCustomerByCusname(String cusname);

    public List<Customer> findCusForEmpCusname(Integer empid, String cusname);
}
