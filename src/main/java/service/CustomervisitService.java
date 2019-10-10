package service;

import entity.Customervisit;

import java.util.List;

public interface CustomervisitService {
    public List<Customervisit> findAllCustomervisit();
    public int subCustomervisit(Integer id);
    public int updateCustomervisit(Customervisit customervisit);
    public Customervisit findCustomervisit(Integer id);
    public int insertCustomervisit(Customervisit customervisit);
    public List<Customervisit> findCustomervisitById(Integer empid);
    public List<Customervisit> findAllCustomervisitBycusname(String cusname);
    public List<Customervisit> findAllCustomervisitByIdcusname(Integer empid,String cusname);
}
