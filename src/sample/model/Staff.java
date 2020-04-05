package sample.model;

import java.io.InputStream;

public class Staff {
    private int id;
    private String firstname,lastname,username,password,mobileno,dob,age,designation,gender,dateofjoinning,address,emailId;
    private InputStream photo;

    public Staff() {
    }

    public Staff(String firstname, String lastname, String username, String password, String mobileno, String dob, String age, String designation,String gender, String dateofjoinning, String address, String emailId,InputStream photo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.mobileno = mobileno;
        this.dob = dob;
        this.age = age;
        this.designation = designation;
        this.gender=gender;
        this.dateofjoinning = dateofjoinning;
        this.address = address;
        this.emailId = emailId;
        this.photo=photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDateofjoinning() {
        return dateofjoinning;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateofjoinning(String dateofjoinning) {
        this.dateofjoinning = dateofjoinning;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }
}
