package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Customershare;
import entity.Employee;
import entity.Emprole;
import entity.Role;
import service.EmployeeService;
import service.EmproleService;
import service.RoleService;

@Controller
public class EmproleController {
	@Autowired
	private EmproleService emproleService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private RoleService roleService;

	@RequestMapping("/ShowAllEmprole")
	public ModelAndView showAllEmployee(HttpServletRequest request, Integer roleid) {
		List<Emprole> list = emproleService.findEmproleForAdmin(null);
		ModelAndView mav = new ModelAndView();
		request.setAttribute("list", list);
		mav.setViewName("/ShowAllEmprole.jsp");
		return mav;
	}

	@ResponseBody
	@RequestMapping("/UpdateEmprole")
	public Map<String, Object> updateEmprole(HttpServletRequest request, Integer id, Integer roleid) {
		Map<String, Object> map = new HashMap<>();
		Emprole emprole = emproleService.findEmproleById(id);
		List<Employee> listEmployee = employeeService.findEmployeeForAdmin();
		List<Role> listRole = roleService.findRoleForAdmin(roleid);
		map.put("empRole", emprole);
		map.put("listEmployee", listEmployee);
		map.put("listRole", listRole);
		return map;
	}

	@ResponseBody
	@RequestMapping("/UpdateEmpRoleHou")
	public Map<String, Object> updateEmproleHou(HttpServletRequest request, Emprole emprole, Integer empid,
			Integer roleid) {
		Map<String, Object> map = new HashMap<>();
		
		// 如果是主管降职则不需要判断 所以根据empid找到该员工的角色
		Emprole oldEmprole = emproleService.findRoleid(empid);
		
		// 如果是专员   修改之前先查询该员工是否有客户 == 该员工还有共享记录
		if (oldEmprole != null && oldEmprole.getRole() != null && oldEmprole.getRole().getRoleid() == 2) {
			List<Customershare> customershare = employeeService.findCusshareByEmpid(empid);
			if (customershare != null && customershare.size() > 0) {
				map.put("code", 500);
				map.put("msg", "该员工尚有客户待处理，请处理后进行职位变更");
				return map;
			}
		}
		
		Employee employee = new Employee();
		employee.setEmpid(empid);
		Role role = new Role();
		role.setRoleid(roleid);
		emprole.setRole(role);
		emprole.setEmp(employee);

		int i = emproleService.updateEmprole(emprole);
		if (i == 1) {
			map.put("code", 200);
			map.put("msg", "更新成功");
		} else {
			map.put("code", 500);
			map.put("msg", "更新失败");
		}
		return map;
	}

}
