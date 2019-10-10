package shiro;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class MyRolesAuthorizationFilter extends AuthorizationFilter {  
	  
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {  
    	
        Subject subject = getSubject(request, response);  
        String[] rolesArray = (String[]) mappedValue;  
  
        if (rolesArray == null || rolesArray.length == 0) {  
            //no roles specified, so nothing to check - allow access.  
            return false;  
        }  
  
        List<String> roles = CollectionUtils.asList(rolesArray);  
        boolean[] hasRoles = subject.hasRoles(roles);  
        for (boolean hasRole : hasRoles) {  
            if (hasRole) {  
                return true;  
            }  
        }  
        return false;  
    }  
} 
