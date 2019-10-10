package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Customer;
import entity.Customervisit;
import entity.Employee;
import entity.Emprole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CustomervisitController {
    @Autowired
    private CustomervisitService customervisitService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmproleService emproleService;
    @RequestMapping("/ShowAllCustomervisit")
    public ModelAndView showAllCustomervisit(HttpServletRequest request) {
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        Integer empid = emp.getEmpid();
        Emprole emprole = emproleService.findRoleid(empid);
        List<Customervisit> list=new ArrayList<Customervisit>();
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 1){
            list = customervisitService.findAllCustomervisit();
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 2){
            list=customervisitService.findCustomervisitById(empid);
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 3){
            list = customervisitService.findAllCustomervisit();
        }
        ModelAndView mav = new ModelAndView();
        request.setAttribute("list", list);
        mav.setViewName("/ShowAllCustomervisit.jsp");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/ShowCustomervisitPage")
    public Map<String, Object> showCustomervisitPage(HttpServletRequest request,int page,int limit,String cusname) {

        Employee emp = (Employee) request.getSession().getAttribute("employee");
        Integer empid = emp.getEmpid();
        Emprole emprole = emproleService.findRoleid(empid);
        Map<String, Object> map = new HashMap<>();
        List<Customervisit> list=new ArrayList<Customervisit>();
        PageHelper.startPage(page, limit);
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 1) {
            list = customervisitService.findAllCustomervisitBycusname(cusname);
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 2){
            list=customervisitService.findAllCustomervisitByIdcusname(emp.getEmpid(),cusname);
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 3) {
            list = customervisitService.findAllCustomervisitBycusname(cusname);
        }
        PageInfo<Customervisit> info = new PageInfo<>(list);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", info.getTotal());
        map.put("data", info.getList());
        return map;
    }

    @RequestMapping("/ShowCVPage")
    public ModelAndView showCVPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/ShowCustomervisitPage.jsp");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/SubCustomervisit")
    public Map<String, Object> subCustomervisit(HttpServletRequest request, int id){
        Map<String, Object> map=new HashMap<>();
        int i=customervisitService.subCustomervisit(id);
        if(i==1){
            map.put("code",200);
            map.put("msg","删除成功");
        }else{
            map.put("code",500);
            map.put("msg","删除失败");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("/UpdateCustomervisit")
    public Map<String, Object> updateCustomervisit(HttpServletRequest request, Integer id) {
        Map<String, Object> map = new HashMap<>();
        Customervisit customervisit = customervisitService.findCustomervisit(id);
        List<Employee> listEmployee = employeeService.findEmpfor2(2);
        List<Customer> listCustomer = customerService.findAllCustomer1();
        map.put("cusVisit",customervisit);
        map.put("listEmployee",listEmployee);
        map.put("listCustomer",listCustomer);
        return map;
    }
    @ResponseBody
    @RequestMapping("/UpdateCusVisit")
    public Map<String, Object> updateCustomervisit(HttpServletRequest request, Customervisit customervisit,Integer cid,Integer empid) {
        Map<String, Object> map = new HashMap<>();
        Employee employee = new Employee();
        employee.setEmpid(empid);
        Customer customer = new Customer();
        customer.setCid(cid);
        customervisit.setCus(customer);
        customervisit.setEmp(employee);
        int i = customervisitService.updateCustomervisit(customervisit);
        if (i == 1) {
            map.put("code", 200);
            map.put("msg", "更新成功");
        } else {
            map.put("code", 500);
            map.put("msg", "更新失败");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("/AddCustomervisit")
    public Map<String, Object> addCustomervisit(HttpServletRequest request, Customervisit customervisit,Integer cid,Integer empid) {
        Map<String, Object> map = new HashMap<>();
        Employee employee = new Employee();
        employee.setEmpid(empid);
        Customer customer = new Customer();
        customer.setCid(cid);
        customervisit.setCus(customer);
        customervisit.setEmp(employee);

        int i = customervisitService.insertCustomervisit(customervisit);
        if (i == 1) {
            map.put("code", 200);
            map.put("msg", "增加成功");
        } else {
            map.put("code", 500);
            map.put("msg", "增加失败");
        }
        return map;
    }
}