package entity;

public class Limitrole {
	
	private int id ;
	
	private int empid;
	
	private int roleid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	@Override
	public String toString() {
		return "Limitrole [id=" + id + ", empid=" + empid + ", roleid=" + roleid + "]";
	}
	
	
	
}
