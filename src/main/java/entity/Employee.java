package entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class Employee implements Serializable {

    private Integer empid;
    @NotEmpty()
    private String username;
    @Length(min=3,message = "密码最少时三位数")
    private String password;
    @Pattern(regexp = "/^[a-z0-9A-Z]+@([a-z|0-9|A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,4}$/")
    private String tel;
    @NotEmpty()
    private String ename;
    @Pattern(regexp = "/^[0-9]{5,10}@[a-z]{2,3}.com$/")
    private String email;
    
    private String salt;

    private boolean remember;

    public Employee() {
    }

    public Employee(Integer empid) {
        this.empid = empid;
    }

    public Employee(String username, String password, String tel, String ename, String email) {
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.ename = ename;
        this.email = email;
    }

    public Employee(Integer empid, String username, String password, String tel, String ename, String email) {
        this.empid = empid;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.ename = ename;
        this.email = email;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
    
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    @Override
	public String toString() {
		return "Employee [empid=" + empid + ", username=" + username + ", password=" + password + ", tel=" + tel
				+ ", ename=" + ename + ", email=" + email + ", salt=" + salt + "]";
	}

	
}