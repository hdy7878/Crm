package controller;

import entity.Customer;
import entity.Employee;
import entity.Emprole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddCustomervisitController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmproleService emproleService;

    @RequestMapping("/toAddCustomervisit")
    public ModelAndView toAddCustomervisit(HttpServletRequest request) {
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        Integer empid = emp.getEmpid();
        Emprole emprole = emproleService.findRoleid(empid);
        List<Employee> listEmployee=new ArrayList<Employee>();
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 1){
            listEmployee=employeeService.findEmpfor2(2);
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 2){
            listEmployee=employeeService.findEmployeeById(empid);
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 3){
            listEmployee=employeeService.findEmpfor2(2);
        }
        List<Customer> listCustomer=customerService.findAllCustomer1();
       request.setAttribute("listEmployee",listEmployee);
        request.setAttribute("listCustomer",listCustomer);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/AddCustomervisit.jsp");
        return mav;
    }
}
