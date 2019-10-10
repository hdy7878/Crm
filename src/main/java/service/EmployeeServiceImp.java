package service;

import entity.Customershare;
import entity.Customervisit;
import entity.Employee;
import entity.Emprole;
import mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("EmployeeService")
@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,readOnly = false)
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeMapper mapper;
    @Override
    public Employee findEmployeeByLogin(Employee employee) {
      return mapper.selectEmployeeByLogin(employee);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return mapper.selectAllEmployee();
    }

    @Override
    public int registEmployee(Employee employee) {

        return mapper.insertEmployee(employee);
    }

    @Override
    public int addEmployee(Employee employee) {
        return mapper.insertEmployee(employee);
    }

    @Override
    public int subEmployee(Integer empid) {
        return mapper.subEmployee(empid);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return mapper.updateEmployee(employee);
    }

    @Override
    public Employee selectEmployeeByUsername(Employee employee) {
        return mapper.selectEmployeeByUsername(employee);
    }

    @Override
    public List<Employee> findEmployeeById(Integer empid) {
        return mapper.selectEmployeeById(empid);
    }

    @Override
    public List<Employee> findEmpfor2(Integer roleid) {
        return mapper.selectEmpFor2(roleid);
    }

    @Override
    public List<Customershare> findCusshareByEmpid(Integer empid) {
        return mapper.selectCusshareByEmpid(empid);
    }

    @Override
    public List<Customervisit> findCusvisitByEmpid(Integer empid) {
        return mapper.selectCusvisitByEmpid(empid);
    }

    @Override
    public List<Employee> findEmployeeForAdmin() {
        return mapper.selectEmployeeForAdmin();
    }

    @Override
    public Emprole findEmproleByEmpid(Integer empid) {
        return mapper.selectEmproleByEmpid(empid);
    }

    @Override
    public int changePassword(Employee employee) {
        return mapper.updatePassword(employee);
    }

    @Override
    public Employee findEmployee(String username) {
        return mapper.selectEmployee(username);
    }

    @Override
    public List<Employee> findAllEmployeeByename(String ename) {
        return mapper.findAllEmployeeByename(ename);
    }

    @Override
    public List<Employee> findEmployeeFor2(String ename) {
        return mapper.findEmployeeFor2(ename);
    }
}
