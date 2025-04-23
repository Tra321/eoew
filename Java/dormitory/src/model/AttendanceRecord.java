package model;

import java.time.LocalDateTime;

public class AttendanceRecord {
    private int recordId;
    private String employeeId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String attendanceStatus; // NORMAL, LATE, EARLY_LEAVE, ABSENT
    private String remarks;
    
    // 无参构造函数
    public AttendanceRecord() {
    }
    
    // 带参构造函数
    public AttendanceRecord(int recordId, String employeeId, LocalDateTime checkInTime, 
                           LocalDateTime checkOutTime, String attendanceStatus, String remarks) {
        this.recordId = recordId;
        this.employeeId = employeeId;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.attendanceStatus = attendanceStatus;
        this.remarks = remarks;
    }
    
    // Getter和Setter方法
    public int getRecordId() {
        return recordId;
    }
    
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }
    
    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }
    
    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }
    
    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
    
    public String getAttendanceStatus() {
        return attendanceStatus;
    }
    
    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}