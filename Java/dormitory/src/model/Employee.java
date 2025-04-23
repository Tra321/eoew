package model;

import java.time.LocalDate;

public class Employee {
    private String employeeId;
    private String employeeName;
    private String employeePassword;
    private String department;
    private String position;
    private String phone;
    private String email;
    private LocalDate hireDate;
    
    // 无参构造函数
    public Employee() {
    }
    
    // 带参构造函数
    public Employee(String employeeId, String employeeName, String employeePassword, String department, 
                   String position, String phone, String email, LocalDate hireDate) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
        this.department = department;
        this.position = position;
        this.phone = phone;
        this.email = email;
        this.hireDate = hireDate;
    }
    
    // Getter和Setter方法
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public String getEmployeePassword() {
        return employeePassword;
    }
    
    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public LocalDate getHireDate() {
        return hireDate;
    }
    
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
}