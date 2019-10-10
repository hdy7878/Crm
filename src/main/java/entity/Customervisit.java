package entity;

public class Customervisit {
    private Integer id;

    private Customer cus;

    private Employee emp;

    private String content;

    private String date;

    public Customervisit() {
    }

    public Customervisit(Customer cus, Employee emp, String content, String date) {
        this.cus = cus;
        this.emp = emp;
        this.content = content;
        this.date = date;
    }

    public Customervisit(Integer id, Customer cus, Employee emp, String content, String date) {
        this.id = id;
        this.cus = cus;
        this.emp = emp;
        this.content = content;
        this.date = date;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Customervisit{" +
                "id=" + id +
                ", cus=" + cus +
                ", emp=" + emp +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}