package model;

public class Admin {
    private String adminId;
    private String adminName;
    private String adminPassword;
    private String phone;
    private String email;
    
    // 无参构造函数
    public Admin() {
    }
    
    // 带参构造函数
    public Admin(String adminId, String adminName, String adminPassword, String phone, String email) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.phone = phone;
        this.email = email;
    }
    
    // Getter和Setter方法
    public String getAdminId() {
        return adminId;
    }
    
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    
    public String getAdminName() {
        return adminName;
    }
    
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    
    public String getAdminPassword() {
        return adminPassword;
    }
    
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
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
} 