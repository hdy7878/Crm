package controller;

import entity.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private SimpleCookie cookie;

    @RequestMapping("/toLogin")
    public ModelAndView toLogin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login.jsp");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/login")
    public Map<String, Object> login(HttpServletRequest request,HttpServletResponse response,Employee employee) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(employee.getUsername(),employee.getPassword());
            System.err.println(employee.isRemember());
            if(employee.isRemember()){
                token.setRememberMe(true);
            }
            subject.login(token);
            Employee dbEmployee = (Employee) subject.getPrincipal();
            if (dbEmployee != null) {
                request.getSession().setAttribute("employee", dbEmployee);

                if(employee.isRemember()){
                    cookie.setValue(dbEmployee.getEmpid().toString());
                }else{
                    cookie.setValue(null);
                }

                map.put("code", 200);
            }else{
                map.put("code", 500);
                map.put("msg", "账号或者密码错误");
            }
        } catch (UnknownAccountException e) {
            map.put("code", 500);
            map.put("msg", "用户名错误");

        } catch (IncorrectCredentialsException e) {
            map.put("code", 500);
            map.put("msg", "密码错误");

        }
        return map;
    }

    @RequestMapping("/loginOut")
    public ModelAndView loginOut(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        request.getSession().removeAttribute("employee");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        mav.setViewName("/login.jsp");
        return mav;
    }
}
