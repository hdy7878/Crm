package service;

import entity.Customershare;
import entity.Customervisit;
import entity.Employee;
import entity.Emprole;

import java.util.List;

public interface EmployeeService {
    public Employee findEmployeeByLogin(Employee employee);
    public List<Employee> findAllEmployee();
    public int registEmployee(Employee employee);
    public int addEmployee(Employee employee);
    public int subEmployee(Integer empid);
    public int updateEmployee(Employee employee);
    public Employee selectEmployeeByUsername(Employee employee);
    public List<Employee> findEmployeeById(Integer empid);
    public List<Employee> findEmpfor2(Integer roleid);
    public List<Customershare> findCusshareByEmpid(Integer empid);

    public List<Customervisit> findCusvisitByEmpid(Integer empid);
    public List<Employee> findEmployeeForAdmin();
    public Emprole findEmproleByEmpid(Integer empid);
    public int changePassword(Employee employee);
    public Employee findEmployee(String username);
    public List<Employee> findAllEmployeeByename(String ename);
    public List<Employee> findEmployeeFor2(String ename);

}
