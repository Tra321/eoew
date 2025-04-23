package service;

import dao.LeaveRequestDao;
import dao.impl.LeaveRequestDaoImpl;
import model.LeaveRequest;
import until.EmployeeMenu;
import until.AdminMenu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class LeaveService {
    private LeaveRequestDao leaveRequestDao = new LeaveRequestDaoImpl();
    private Scanner sc = new Scanner(System.in);
    
    public void submitLeaveRequest(String employeeId) {
        LeaveRequest request = new LeaveRequest();
        request.setEmployeeId(employeeId);
        
        System.out.println("请输入请假类型 (SICK, PERSONAL, VACATION)：");
        String leaveType = sc.nextLine();
        request.setLeaveType(leaveType);
        
        System.out.println("请输入请假开始时间 (yyyy-MM-dd HH:mm)：");
        String startTimeStr = sc.nextLine();
        
        System.out.println("请输入请假结束时间 (yyyy-MM-dd HH:mm)：");
        String endTimeStr = sc.nextLine();
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            request.setStartTime(LocalDateTime.parse(startTimeStr, formatter));
            request.setEndTime(LocalDateTime.parse(endTimeStr, formatter));
            
            System.out.println("请输入请假原因：");
            String reason = sc.nextLine();
            request.setReason(reason);
            
            request.setStatus("PENDING");
            
            if (leaveRequestDao.submitLeaveRequest(request)) {
                System.out.println("请假申请提交成功");
            } else {
                System.out.println("请假申请提交失败");
            }
            
        } catch (Exception e) {
            System.out.println("日期时间格式错误，请使用yyyy-MM-dd HH:mm格式");
        }
        
        EmployeeMenu.showMenu(employeeId);
    }
    
    public void viewMyLeaveRequests(String employeeId) {
        List<LeaveRequest> requests = leaveRequestDao.getLeaveRequestsByEmployee(employeeId);
        
        if (requests.isEmpty()) {
            System.out.println("没有请假记录");
            EmployeeMenu.showMenu(employeeId);
            return;
        }
        
        System.out.println("请假记录：");
        System.out.println("ID\t类型\t开始时间\t\t结束时间\t\t状态\t\t原因\t\t审批备注");
        for (LeaveRequest request : requests) {
            System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%s\n",
                    request.getRequestId(),
                    request.getLeaveType(),
                    request.getStartTime(),
                    request.getEndTime(),
                    request.getStatus(),
                    request.getReason(),
                    request.getApproverRemarks() != null ? request.getApproverRemarks() : "");
        }
        
        EmployeeMenu.showMenu(employeeId);
    }
    
    // 管理员审批请假申请
    public void approveLeaveRequests() {
        List<LeaveRequest> pendingRequests = leaveRequestDao.getPendingLeaveRequests();
        
        if (pendingRequests.isEmpty()) {
            System.out.println("没有待审批的请假申请");
            return;
        }
        
        System.out.println("待审批的请假申请：");
        System.out.println("ID\t员工ID\t类型\t开始时间\t\t结束时间\t\t原因");
        for (LeaveRequest request : pendingRequests) {
            System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\n",
                    request.getRequestId(),
                    request.getEmployeeId(),
                    request.getLeaveType(),
                    request.getStartTime(),
                    request.getEndTime(),
                    request.getReason());
        }
        
        System.out.println("请输入要审批的申请ID：");
        int requestId = Integer.parseInt(sc.nextLine());
        
        System.out.println("请输入审批结果 (APPROVED, REJECTED)：");
        String status = sc.nextLine();
        
        System.out.println("请输入审批备注：");
        String remarks = sc.nextLine();
        
        if (leaveRequestDao.approveLeaveRequest(requestId, status, remarks)) {
            System.out.println("审批成功");
        } else {
            System.out.println("审批失败");
        }
    }
}