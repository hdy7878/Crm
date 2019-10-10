package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import entity.Customer;
import entity.Customershare;
import entity.Customervisit;
import entity.Employee;
import entity.Emprole;
import service.*;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmproleService emproleService;
    @Autowired
    private CustomershareService customershareService;

    /* 查询所有客户信息 */
    @RequestMapping("/ShowAllCustomer1")
    public ModelAndView showAllCustomer(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        Integer empid = emp.getEmpid();
        Emprole emprole = emproleService.findRoleid(empid);
        List<Customer> list = new ArrayList<Customer>();
        if (emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 1) {
            list = customerService.findAllCustomer1();
        }
        if (emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 2) {
            list = customerService.findCusForEmp(emp.getEmpid());
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 3){
           list=customerService.findAllCustomer1();
        }
        mav.addObject("list", list);
        mav.setViewName("/ShowAllCustomer.jsp");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/ShowCustomerPage")
    public Map<String, Object> showCustomerPage(HttpServletRequest request,int page,int limit,String cusname) {

        Employee emp = (Employee) request.getSession().getAttribute("employee");
        Integer empid = emp.getEmpid();
        Emprole emprole = emproleService.findRoleid(empid);
        Map<String, Object> map = new HashMap<>();
        List<Customer> list=new ArrayList<Customer>();
        PageHelper.startPage(page, limit);
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 1) {
            list = customerService.findAllCustomerByCusname(cusname);
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 2){
            list = customerService.findCusForEmpCusname(emp.getEmpid(),cusname);
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 3){
            list = customerService.findAllCustomerByCusname(cusname);
        }
        PageInfo<Customer> info = new PageInfo<>(list);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", info.getTotal());
        map.put("data", info.getList());
        return map;
    }

    @RequestMapping("/ShowCusPage")
    public ModelAndView showCusPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/ShowCustomerPage.jsp");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/UpdateCustomerById")
    public Map<String, Object> showCustomerById(HttpServletRequest request, Integer cid) {
        Map<String, Object> map = new HashMap<>();
        Customer customer = customerService.findCustomerById(cid);
        System.err.println(customer);
        if (customer != null) {
            map.put("code", 200);
            map.put("customer", customer);
        } else {
            map.put("code", 500);
            map.put("msg", "没信息");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/UpdateCustomer")
    public Map<String, Object> updateCustomer(HttpServletRequest request, Customer customer) {
        Map<String, Object> map = new HashMap<>();
        int i = customerService.updateCustomer(customer);
        if (i == 1) {
            map.put("code", 200);
            map.put("msg", "修改成功");
        } else {
            map.put("code", 500);
            map.put("msg", "修改失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/InsertCustomer")
    public Map<String, Object> insertCustomer(HttpServletRequest request, Customer customer, Employee employee) {
        Map<String, Object> map = new HashMap<>();

        int i = customerService.insertCustomer(customer);
        if (i == 1) {
            Customershare customershare = new Customershare();
            customershare.setCus(customer);
            customershare.setEmp(employee);
            int addFlag = customershareService.insertCustomershare(customershare);
            if (addFlag == 1) {
                map.put("code", 200);
                map.put("msg", "增加成功");
            } else {
                map.put("code", 500);
                map.put("msg", "增加失败");
            }
        } else {
            map.put("code", 500);
            map.put("msg", "增加失败");
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/SubCustomer")
    public Map<String, Object> subCustomer(HttpServletRequest request, int cid) {
        Map<String, Object> map = new HashMap<>();

        // 查询是否有共享记录
        List<Customershare> list1 = customerService.findCusshareByCid(cid);
        System.err.println(list1);
        // 查询是否有访问记录
        List<Customervisit> list2 = customerService.findCusvisitByCid(cid);

        if (list1.size()!=0 || list2.size() != 0 ) {
            map.put("code", 500);
            map.put("msg", "客户尚有业务待完成，请完成后进行删除");
            return map;
        }else {
            int i = customerService.subCustomer(cid);
            if (i == 1) {
                map.put("code", 200);
                map.put("msg", "删除成功");
            } else {
                map.put("code", 500);
                map.put("msg", "删除失败");
            }
            return map;
        }
    }

    @RequestMapping("/downExcel")
    public ModelAndView downLoad(HttpServletRequest request, Map<String, Object> map) {
        List<Customer> list = customerService.findAllCustomer1();
        ModelAndView mav = new ModelAndView();
        map.put("list", list);
        mav.addObject("map", map);
        mav.setViewName("myExcelView");
        return mav;
    }

}
