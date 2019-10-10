package controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.*;

@Controller
public class EmployeeController {
    @Autowired
    private EmproleService emproleService;
    @Autowired
    private CustomershareService customershareService;
    @Autowired
    private EmployeeService employeeService;
   @Autowired
   private CustomervisitService customervisitService;

    @RequestMapping("/ShowEmployeeByEmpId")
    public ModelAndView showEmployeeByEmpId(HttpServletRequest request) {
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        Integer empid = emp.getEmpid();
        List<Employee> list= employeeService.findEmployeeById(empid);
        System.err.println(list);
        ModelAndView mav = new ModelAndView();
        request.setAttribute("list", list);
        mav.setViewName("/ShowOwnMessage.jsp");
        return mav;
    }

    @RequestMapping("/ShowAllEmployee")
    public ModelAndView showAllEmployee(HttpServletRequest request) {

        Integer roleid = 2;
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        Integer empid = emp.getEmpid();
        Emprole emprole = emproleService.findRoleid(empid);
        List<Employee> list=new ArrayList<Employee>();
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 3){
            list=employeeService.findEmployeeForAdmin();
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 1){
            list= employeeService.findEmpfor2(roleid);
        }
        ModelAndView mav = new ModelAndView();
        request.setAttribute("list", list);
        mav.setViewName("/ShowAllEmployee.jsp");
        return mav;
    }
    @ResponseBody
    @RequestMapping("/ShowEmployeePage")
    public Map<String, Object> showEmployeePage(HttpServletRequest request,int page,int limit,String ename) {
        Employee emp = (Employee) request.getSession().getAttribute("employee");
        Integer empid = emp.getEmpid();
        Emprole emprole = emproleService.findRoleid(empid);
        List<Employee> list=new ArrayList<Employee>();
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(page, limit);
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 3){
            list=employeeService.findAllEmployeeByename(ename);
        }
        if(emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 1){
            list= employeeService.findEmployeeFor2(ename);
        }
        PageInfo<Employee> info = new PageInfo<>(list);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", info.getTotal());
        map.put("data", info.getList());
        return map;
    }

    @RequestMapping("/ShowEmpPage")
    public ModelAndView showEmpPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/ShowEmployeePage.jsp");
        return mav;
    }
    @ResponseBody
    @RequestMapping("/regist")
    public Map<String, Object> login(HttpServletRequest request, Employee employee,Role role) {
        Map<String, Object> map = new HashMap<>();
        Employee alreadyemployee = employeeService.selectEmployeeByUsername(employee);
        if (alreadyemployee != null) {
            map.put("code", 500);
            map.put("msg", "用户名已经存在");
            return map;
        }
        //生成盐（部分，需要存入数据库中）
        String random=new SecureRandomNumberGenerator().nextBytes().toHex();
        //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
        String md5Password = new Md5Hash(employee.getPassword(),random,3).toString();

        employee.setSalt(random);
        employee.setPassword(md5Password);

        int i=employeeService.registEmployee(employee);
        if(i==1){
            Emprole emprole1=new Emprole();
            emprole1.setEmp(employee);
            emprole1.setRole(role);
            int addFlag=emproleService.addEmprole(emprole1);
            if(addFlag==1){
                map.put("code", 200);
                map.put("msg","注册成功");
            }else{
                map.put("code", 500);
                map.put("msg", "注册失败");
            }
        }else{
            map.put("code", 500);
            map.put("msg", "注册失败");
        }

        return map;
    }
    @ResponseBody
    @RequestMapping("/InsertEmployee")
    public Map<String, Object> insertEmployee(HttpServletRequest request, Employee employee,Role role){
        Map<String, Object> map = new HashMap<>();
        Employee addemployee = employeeService.selectEmployeeByUsername(employee);
        if (addemployee != null) {
            map.put("code", 500);
            map.put("msg", "用户名已经存在");
            return map;
        }
        //生成盐（部分，需要存入数据库中）
        String random=new SecureRandomNumberGenerator().nextBytes().toHex();
        //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
        String md5Password = new Md5Hash(employee.getPassword(),random,3).toString();

        employee.setSalt(random);
        employee.setPassword(md5Password);

        int i=employeeService.registEmployee(employee);

        if(i==1){
            Emprole emprole=new Emprole();
            emprole.setEmp(employee);
            emprole.setRole(role);
            int addFlag=emproleService.addEmprole(emprole);
            if(addFlag==1){
                map.put("code", 200);
                map.put("msg","增加成功");
            }else{
                map.put("code", 500);
                map.put("msg", "增加失败");
            }
        }else{
            map.put("code", 500);
            map.put("msg", "增加失败");
        }

        return map;
    }
    @ResponseBody
    @RequestMapping("/DeleteEmployee")
    public Map<String, Object> deleteEmoloyee(HttpServletRequest request, int empid) {
        Map<String, Object> map = new HashMap<>();
        // 查询是否有共享记录
        List<Customershare> list1 = employeeService.findCusshareByEmpid(empid);

        // 查询是否有访问记录
        List<Customervisit> list2 = employeeService.findCusvisitByEmpid(empid);



        if (list1.size() != 0 || list2.size() != 0) {
            map.put("code", 500);
            map.put("msg", "客户尚有业务待完成，请完成后进行删除");
            return map;
        }else {
             int i=emproleService.subEmprole(empid);
             if(i==1){
                 int subFlag = employeeService.subEmployee(empid);
                 if (subFlag == 1) {
                     map.put("code", 200);
                     map.put("msg", "删除成功");
                 } else {
                     map.put("code", 500);
                     map.put("msg", "删除失败");
                 }
                 return map;
             } else {
                 map.put("code", 500);
                 map.put("msg", "删除失败");
             }
            return map;

        }

    }
    /*改密码*/
    @RequestMapping("/toMessage")
    public ModelAndView toMessage(HttpServletRequest request){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/ChangePassword.jsp");
        return mav;
    }
    /*修改密码*/
    @ResponseBody
    @RequestMapping("/ChangeMessage")
    public Map<String,Object> changeMessage(HttpServletRequest request,String oldPassword,String newPassword){
        Map<String,Object> map=new HashMap<String, Object>();
        Employee employee=(Employee)request.getSession().getAttribute("employee");
      /*  String oldMd5Password=new Md5Hash(oldPassword,employee.getSalt(),3).toString();
        if(!employee.getPassword().equals(oldMd5Password)){
              map.put("code",500);
              map.put("msg","输入的密码有误");
              return map;
        }*/
        String random=new SecureRandomNumberGenerator().nextBytes().toHex();
        String newMd5Password = new Md5Hash(newPassword,random,3).toString();
        employee.setSalt(random);
        employee.setPassword(newMd5Password);
        int i=employeeService.changePassword(employee);
        if(i==1){
            map.put("code",200);
            map.put("msg","修改密码成功");
        }else{
            map.put("code",500);
            map.put("msg","密码修改失败");
        }
        return map;
    }
    /*密码验证*/
    @ResponseBody
    @RequestMapping("/CheckOldPassWord")
    public boolean checkOldPassWord(HttpServletRequest request,String oldPassWord){
        Employee employee=(Employee)request.getSession().getAttribute("employee");

        String oldMd5Password=new Md5Hash(oldPassWord,employee.getSalt(),3).toString();

        if(employee.getPassword().equals(oldMd5Password)){
            return true;
        }else{
            return false;
        }
    }
    /*增加时用户名验证*/
    @ResponseBody
    @RequestMapping("/Checkusername")
    public boolean checkusername(HttpServletRequest request,String username){
        Employee employee=(Employee)request.getSession().getAttribute("employee");
        Employee employee1=employeeService.findEmployee(username);
        if(employee1!=null){
            return false;
        }else{
            return true;
        }
    }
    /*注册时用户名验证*/
    @ResponseBody
    @RequestMapping("/CheckUsername")
    public boolean checkUsername(HttpServletRequest request,String registUsername){

       Employee employee1=employeeService.findEmployee(registUsername);

        if(employee1!=null){
            return false;
        }else{
            return true;
        }
    }

    @ResponseBody
    @RequestMapping("/UpdateEmployeeById")
    public Map<String, Object> updateEmployeeById(HttpServletRequest request,Integer empid) {
        System.err.println(empid);
        Map<String, Object> map = new HashMap<>();
        List<Employee> list=employeeService.findEmployeeById(empid);

        if(list!=null){
            map.put("code",200);
            map.put("employee",list);
        }else{
            map.put("code",500);
            map.put("msg","没信息");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("/UpdateEmployee")
    public Map<String, Object> updateEmployee(HttpServletRequest request, Employee employee){
        Map<String, Object> map=new HashMap<>();
        int i=employeeService.updateEmployee(employee);
        if(i==1){
            map.put("code",200);
            map.put("msg","修改成功");
        }else{
            map.put("code",500);
            map.put("msg","修改失败");
        }
        return map;
    }
}

