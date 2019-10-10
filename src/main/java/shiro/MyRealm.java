package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;

import entity.Employee;
import entity.Emprole;
import service.EmployeeService;
import service.EmproleService;


public class MyRealm extends AuthorizingRealm {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmproleService emproleService;

	@Autowired
	private SimpleCookie cookie;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

		System.out.println("执行授权逻辑....");

		// 对用户进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 1.到数据库查询当前登录用户有什么权限
		Subject subject = SecurityUtils.getSubject();

		Employee dbEmployee = (Employee) subject.getPrincipal();

		// 未勾选记录我 shiro中用户为空
		if (dbEmployee == null) {
			return null;
		}
		if (cookie.getValue() == null) {
			Employee sessionEmployee = (Employee) subject.getSession().getAttribute("employee");
			if (sessionEmployee == null) {
				return null;
			}
		}

		subject.getSession().setAttribute("employee", dbEmployee);

		Emprole emprole = emproleService.findRoleid(dbEmployee.getEmpid());


		if (emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 1) {
			info.addRole("zhuguan");
		}
		if (emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 2) {
			info.addRole("zhuanyuan");
		}
		if (emprole != null && emprole.getRole() != null && emprole.getRole().getRoleid() == 3) {
			info.addRole("admin");
		}

		
		/*List<String> grantKeys = employeeService.findMyGrantKeys(dbEmployee.getEmpid());
		// 2.取出权限字符串(grantKey)
		if (grantKeys != null) {
			for (String grantKey : grantKeys) {
				if (StringUtils.isNotBlank(grantKey)) {
					// 3.把授权字符串加入到info对象中
					info.addStringPermission(grantKey);
				}
			}
		}*/
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行认证逻辑。。。。");

		// 获取用户输入的账户信息
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		// 1.判断用户是否存在

		Employee employee = new Employee();
		employee.setUsername(token.getUsername());

		Employee dbEmployee = employeeService.selectEmployeeByUsername(employee);
		if (dbEmployee == null) {
			// 账户不存在
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(dbEmployee, dbEmployee.getPassword(),getName());
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(dbEmployee.getSalt()));

		// 2.返回数据库 密码
		return authenticationInfo ;
	}

}
