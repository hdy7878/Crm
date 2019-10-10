package entity;

public class Emprole {
	private int id;
	private Employee emp;
	private Role role;

	public Emprole() {
	}

	public Emprole(Employee emp, Role role) {
		this.emp = emp;
		this.role = role;
	}

	public Emprole(int id, Employee emp, Role role) {
		this.id = id;
		this.emp = emp;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Emprole{" + "id=" + id + ", emp=" + emp + ", role=" + role + '}';
	}
}
