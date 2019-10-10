package entity;

public class Customershare {
    private Integer id;

    private Customer cus;

    private Employee emp;

    public Customershare() {
    }

    public Customershare(Customer cus, Employee emp) {
        this.cus = cus;
        this.emp = emp;
    }

    public Customershare(Integer id, Customer cus, Employee emp) {
        this.id = id;
        this.cus = cus;
        this.emp = emp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        return "Customershare{" +
                "id=" + id +
                ", cus=" + cus +
                ", emp=" + emp +
                '}';
    }
}