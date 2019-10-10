package controller;

import entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class AddCusController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/toAddCus")
    public ModelAndView toAddCus(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        List<Employee> listEmployee=employeeService.findEmpfor2(2);
        request.setAttribute("listEmployee",listEmployee);
        mav.setViewName("/AddCustomer.jsp");
        return mav;
    }
}