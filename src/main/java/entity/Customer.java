package entity;

public class Customer {
    private Integer cid;

    private String cusname;

    private String address;

    private String contact;

    private String tel;

    private String email;

    public Customer() {
    }

    public Customer(Integer cid) {
        this.cid = cid;
    }

    public Customer(String cusname, String address, String contact, String tel, String email) {
        this.cusname = cusname;
        this.address = address;
        this.contact = contact;
        this.tel = tel;
        this.email = email;
    }

    public Customer(Integer cid, String cusname, String address, String contact, String tel, String email) {
        this.cid = cid;
        this.cusname = cusname;
        this.address = address;
        this.contact = contact;
        this.tel = tel;
        this.email = email;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname == null ? null : cusname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }


    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", cusname='" + cusname + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}