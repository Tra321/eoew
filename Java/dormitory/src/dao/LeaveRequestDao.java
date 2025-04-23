package dao;

import model.LeaveRequest;
import java.util.List;

public interface LeaveRequestDao {
    boolean submitLeaveRequest(LeaveRequest request);
    List<LeaveRequest> getPendingLeaveRequests();
    List<LeaveRequest> getLeaveRequestsByEmployee(String employeeId);
    boolean approveLeaveRequest(int requestId, String status, String remarks);
}