package dao.impl;

import dao.LeaveRequestDao;
import model.LeaveRequest;
import until.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDaoImpl implements LeaveRequestDao {

    @Override
    public boolean submitLeaveRequest(LeaveRequest request) {
        String sql = "INSERT INTO leave_request (employee_id, start_time, end_time, leave_type, reason, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        return BaseDao.executeUpdate(sql, 
                request.getEmployeeId(), 
                request.getStartTime(), 
                request.getEndTime(),
                request.getLeaveType(),
                request.getReason(),
                request.getStatus()) > 0;
    }

    @Override
    public List<LeaveRequest> getPendingLeaveRequests() {
        String sql = "SELECT * FROM leave_request WHERE status = 'PENDING' ORDER BY start_time";
        return BaseDao.executeQuery(rs -> {
            List<LeaveRequest> requests = new ArrayList<>();
            while (rs.next()) {
                requests.add(mapLeaveRequest(rs));
            }
            return requests;
        }, sql);
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsByEmployee(String employeeId) {
        String sql = "SELECT * FROM leave_request WHERE employee_id = ? ORDER BY start_time DESC";
        return BaseDao.executeQuery(rs -> {
            List<LeaveRequest> requests = new ArrayList<>();
            while (rs.next()) {
                requests.add(mapLeaveRequest(rs));
            }
            return requests;
        }, sql, employeeId);
    }

    @Override
    public boolean approveLeaveRequest(int requestId, String status, String remarks) {
        String sql = "UPDATE leave_request SET status = ?, approver_remarks = ? WHERE request_id = ?";
        return BaseDao.executeUpdate(sql, status, remarks, requestId) > 0;
    }
    
    /**
     * 从ResultSet映射为LeaveRequest对象
     */
    private LeaveRequest mapLeaveRequest(ResultSet rs) throws SQLException {
        LeaveRequest request = new LeaveRequest();
        request.setRequestId(rs.getInt("request_id"));
        request.setEmployeeId(rs.getString("employee_id"));
        
        // 处理时间
        java.sql.Timestamp startTimestamp = rs.getTimestamp("start_time");
        if (startTimestamp != null) {
            request.setStartTime(startTimestamp.toLocalDateTime());
        }
        
        java.sql.Timestamp endTimestamp = rs.getTimestamp("end_time");
        if (endTimestamp != null) {
            request.setEndTime(endTimestamp.toLocalDateTime());
        }
        
        request.setLeaveType(rs.getString("leave_type"));
        request.setReason(rs.getString("reason"));
        request.setStatus(rs.getString("status"));
        request.setApproverRemarks(rs.getString("approver_remarks"));
        
        return request;
    }
} 