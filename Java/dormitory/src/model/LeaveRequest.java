package model;

import java.time.LocalDateTime;

public class LeaveRequest {
    private int requestId;
    private String employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String leaveType; // SICK, PERSONAL, VACATION
    private String reason;
    private String status; // PENDING, APPROVED, REJECTED
    private String approverRemarks;
    
    // 无参构造函数
    public LeaveRequest() {
    }
    
    // 带参构造函数
    public LeaveRequest(int requestId, String employeeId, LocalDateTime startTime, LocalDateTime endTime,
                       String leaveType, String reason, String status, String approverRemarks) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.leaveType = leaveType;
        this.reason = reason;
        this.status = status;
        this.approverRemarks = approverRemarks;
    }
    
    // Getter和Setter方法
    public int getRequestId() {
        return requestId;
    }
    
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public LocalDateTime getStartTime() {
        return startTime;
    }
    
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    
    public LocalDateTime getEndTime() {
        return endTime;
    }
    
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    
    public String getLeaveType() {
        return leaveType;
    }
    
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getApproverRemarks() {
        return approverRemarks;
    }
    
    public void setApproverRemarks(String approverRemarks) {
        this.approverRemarks = approverRemarks;
    }
}