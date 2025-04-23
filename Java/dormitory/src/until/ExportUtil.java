package until;

import model.AttendanceRecord;
import model.LeaveRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExportUtil {
    
    public static void exportAttendanceRecords(List<AttendanceRecord> records, String employeeId) {
        try {
            String fileName = "attendance_" + employeeId + "_" + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".csv";
            
            FileWriter writer = new FileWriter(fileName);
            writer.write("记录ID,员工ID,签到时间,签退时间,考勤状态,备注\n");
            
            for (AttendanceRecord record : records) {
                writer.write(String.format("%d,%s,%s,%s,%s,%s\n",
                        record.getRecordId(),
                        record.getEmployeeId(),
                        record.getCheckInTime(),
                        record.getCheckOutTime() != null ? record.getCheckOutTime() : "",
                        record.getAttendanceStatus(),
                        record.getRemarks() != null ? record.getRemarks() : ""));
            }
            
            writer.close();
            System.out.println("报表已导出到：" + fileName);
            
        } catch (IOException e) {
            System.out.println("导出报表失败：" + e.getMessage());
        }
    }
    
    public static void exportLeaveRequests(List<LeaveRequest> requests) {
        try {
            String fileName = "leave_requests_" + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".csv";
            
            FileWriter writer = new FileWriter(fileName);
            writer.write("申请ID,员工ID,开始时间,结束时间,请假类型,原因,状态,审批备注\n");
            
            for (LeaveRequest request : requests) {
                writer.write(String.format("%d,%s,%s,%s,%s,%s,%s,%s\n",
                        request.getRequestId(),
                        request.getEmployeeId(),
                        request.getStartTime(),
                        request.getEndTime(),
                        request.getLeaveType(),
                        request.getReason(),
                        request.getStatus(),
                        request.getApproverRemarks() != null ? request.getApproverRemarks() : ""));
            }
            
            writer.close();
            System.out.println("报表已导出到：" + fileName);
            
        } catch (IOException e) {
            System.out.println("导出报表失败：" + e.getMessage());
        }
    }
    
    public static void exportAbnormalAttendance(List<AttendanceRecord> records) {
        try {
            String fileName = "abnormal_attendance_" + 
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".csv";
            
            FileWriter writer = new FileWriter(fileName);
            writer.write("记录ID,员工ID,签到时间,签退时间,考勤状态,备注\n");
            
            for (AttendanceRecord record : records) {
                writer.write(String.format("%d,%s,%s,%s,%s,%s\n",
                        record.getRecordId(),
                        record.getEmployeeId(),
                        record.getCheckInTime(),
                        record.getCheckOutTime() != null ? record.getCheckOutTime() : "",
                        record.getAttendanceStatus(),
                        record.getRemarks() != null ? record.getRemarks() : ""));
            }
            
            writer.close();
            System.out.println("报表已导出到：" + fileName);
            
        } catch (IOException e) {
            System.out.println("导出报表失败：" + e.getMessage());
        }
    }
}