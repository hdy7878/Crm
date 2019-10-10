package mapper;

import java.util.List;

import entity.Customer;
import entity.Customershare;
import entity.Customervisit;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    public int insertCustomer(Customer customer);
    public int subCustomer(Integer cid);
    public int updateCustomer(Customer customer);
    public List<Customer> selectAllCustomer();
    public List<Customer> selectAllCustomer1();
    public Customer selectCustomerById(Integer cid);

	public List<Customer> selectCusForEmp(Integer empid);
	public List<Customershare> findCusshareByCid(int cid);
	public List<Customervisit> findCusvisitByCid(int cid);
    public List<Customer> findAllCustomerByCusname(@Param("cusname") String cusname);
    public List<Customer> findCusForEmpCusname(@Param("empid") Integer empid, @Param("cusname") String cusname);

}
