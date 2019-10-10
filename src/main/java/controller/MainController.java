package controller;

import entity.Employee;
import entity.Emprole;
import entity.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.EmproleService;
import service.MainService;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;
    @Autowired
    private EmproleService emproleService;
    @RequestMapping("/toMain")
    public ModelAndView toMain(HttpServletRequest request){
        ModelAndView mav=new ModelAndView();
        Employee employee=(Employee) request.getSession().getAttribute("employee");
        List<Limit> listLimit=mainService.findAllLimit(employee.getEmpid());

        request.setAttribute("listLimit",listLimit);
        Emprole emprole=emproleService.findRoleid(employee.getEmpid());
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 1){
            request.getSession().setAttribute("roleName","合作主管");

        }if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 2){
            request.getSession().setAttribute("roleName","合作专员");

        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 3){
            request.getSession().setAttribute("roleName","董事长");

        }
        mav.setViewName("/main.jsp");
        return mav;
    }
}
