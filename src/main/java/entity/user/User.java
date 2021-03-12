package entity.user;

public class User {
    
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;
    //Data COupling: DUng tham so de thuc hien luong thuc thi
    public User(int id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public User cloneInformation() {
        return new User(this.id, this.name, this.email, this.address, this.phone);
    }
    
    // override toString method
    @Override
    public String toString() {
        return "{" +
            "  username='" + name + "'" +
            ", email='" + email + "'" +
            ", address='" + address + "'" +
            ", phone='" + phone + "'" +
            "}";
    }

    // getter and setter
    public String getName() {
        return this.name;
    }
    //Data COupling: DUng tham so de thuc hien luong thuc thi
    public void setusername(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }
    //Data COupling: DUng tham so de thuc hien luong thuc thi
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }
    //Data COupling: DUng tham so de thuc hien luong thuc thi
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }
    //Data COupling: DUng tham so de thuc hien luong thuc thi
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
