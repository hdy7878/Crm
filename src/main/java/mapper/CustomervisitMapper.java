package mapper;

import entity.Customervisit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomervisitMapper {
    public int insertCustomervisit(Customervisit customervisit);
    public int subCustomervisit(Integer id);
    public int updateCustomervisit(Customervisit customervisit);
    public Customervisit selectCustomervisit(Integer id);
    public List<Customervisit> selectAllCustomervisit();
    public List<Customervisit> selectCustomervisitById(Integer empid);
    /*通过模糊查询所有的访问记录*/
    public List<Customervisit> findAllCustomervisitBycusname(@Param("cusname") String cusname);
    /*通过模糊查询专员自己下面的访问记录*/
    public List<Customervisit> findAllCustomervisitByIdcusname(@Param("empid") Integer empid,@Param("cusname") String cusname);
}
