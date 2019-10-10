package mapper;

import entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    public int insertEmployee(Employee employee);
    public int subEmployee(Integer empid);
    public int updateEmployee(Employee employee);
    public Employee selectEmployeeByLogin(Employee employee);
    public List<Employee> selectAllEmployee();
    public Employee selectEmployeeByUsername(Employee employee);
    public List<Employee> selectEmployeeById(Integer empid);

    public List<Employee> selectEmpFor2(Integer roleid);
    public List<Customershare> selectCusshareByEmpid(Integer empid);
    public List<Customervisit> selectCusvisitByEmpid(Integer empid);
    public Emprole selectEmproleByEmpid(Integer empid);
    public int updatePassword(Employee employee);
    public Employee selectEmployee(String username);
    public List<Employee> selectEmployeeForAdmin();
    /*通过员工名模糊查询所有专员和主管*/
    public List<Employee> findAllEmployeeByename(@Param("ename") String ename);
    /*通过员工名模糊查询所有专员*/
    public List<Employee> findEmployeeFor2(@Param("ename") String ename);

}
