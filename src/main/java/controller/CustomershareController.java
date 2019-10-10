package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.*;
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
public class CustomershareController {
    @Autowired
    private CustomershareService customershareService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmproleService emproleService;

    @RequestMapping("/ShowAllCustomershare")
    public ModelAndView showAllCustomershare(HttpServletRequest request) {
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        Integer empid = emp.getEmpid();
        Emprole emprole = emproleService.findRoleid(empid);
        List<Customershare> list=new ArrayList<Customershare>();

        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 1){
            list = customershareService.findAllCustomershare();
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 2){
            list=customershareService.findCustomershareByEmpId(empid);
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 3){
            list = customershareService.findAllCustomershare();
        }
        ModelAndView mav = new ModelAndView();
        request.setAttribute("list", list);
        mav.setViewName("/ShowAllCustomershare.jsp");
        return mav;
    }
    @ResponseBody
    @RequestMapping("/ShowCustomersharePage")
    public Map<String, Object> showCustomersharePage(HttpServletRequest request,int page,int limit,String cusname) {

        Employee emp = (Employee) request.getSession().getAttribute("employee");
        Integer empid = emp.getEmpid();
        Emprole emprole = emproleService.findRoleid(empid);
        Map<String, Object> map = new HashMap<>();
        List<Customershare> list=new ArrayList<Customershare>();
        PageHelper.startPage(page, limit);
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 1) {
            list = customershareService.findAllCustomershareBycusname(cusname);
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 2){
            list=customershareService.findAllCustomershareByIdcusname(emp.getEmpid(),cusname);
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 3){
            list = customershareService.findAllCustomershareBycusname(cusname);
        }
        PageInfo<Customershare> info = new PageInfo<>(list);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", info.getTotal());
        map.put("data", info.getList());
        return map;
    }

    @RequestMapping("/ShowCSPage")
    public ModelAndView showCSPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/ShowCustomersharePage.jsp");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/SubCustomershare")
    public Map<String, Object> subCustomershare(HttpServletRequest request, int id,int cid){
        Map<String, Object> map=new HashMap<>();
        int i=customershareService.subCustomershare(id);
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
    @RequestMapping("/UpdateCustomershare")
    public Map<String, Object> updateCustomershare(HttpServletRequest request, Integer id) {
        Map<String, Object> map = new HashMap<>();
        Customershare customershare = customershareService.findCustomershareById(id);
        
        List<Employee> listEmployee = employeeService.findEmpfor2(2);
        
        List<Customer> listCustomer = customerService.findAllCustomer1();
        
        map.put("cusShare",customershare);
        map.put("listEmployee",listEmployee);
        map.put("listCustomer",listCustomer);
        return map;
    }
    @ResponseBody
    @RequestMapping("/UpdateCusShare")
    public Map<String, Object> updateCustomershare(HttpServletRequest request, Customershare customershare,Integer cid,Integer empid) {
        Map<String, Object> map = new HashMap<>();

        Customershare oldCustomershare = customershareService.findCustomershareByTerm(empid,cid);

        if (oldCustomershare != null) {
            map.put("code", 500);
            map.put("msg", "共享记录已经存在，请勿重复添加");
            return map;
        }
        Employee employee = new Employee();
        employee.setEmpid(empid);
        Customer customer = new Customer();
        customer.setCid(cid);
        customershare.setCus(customer);
        customershare.setEmp(employee);
        int i = customershareService.updateCustomershare(customershare);
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
    @RequestMapping("/AddCustomershare")
    public Map<String, Object> addCustomershare(HttpServletRequest request, Customershare customershare,Integer cid,Integer empid) {
        Map<String, Object> map = new HashMap<>();
        
        // 判断共享记录是否已经存在
        Customershare oldCustomershare = customershareService.findCustomershareByTerm(empid,cid);
        
        if (oldCustomershare != null) {
        	map.put("code", 500);
            map.put("msg", "共享记录已经存在，请勿重复添加");
            return map;
		}
        
        
        Employee employee = new Employee();
        employee.setEmpid(empid);
        Customer customer = new Customer();
        customer.setCid(cid);
        customershare.setCus(customer);
        customershare.setEmp(employee);

        int i = customershareService.insertCustomershare(customershare);
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

