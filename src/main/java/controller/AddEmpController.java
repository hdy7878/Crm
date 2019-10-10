package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddEmpController {
    @RequestMapping("/toAddEmp")
    public ModelAndView toAddCus(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/AddEmployee.jsp");
        return mav;
    }
}