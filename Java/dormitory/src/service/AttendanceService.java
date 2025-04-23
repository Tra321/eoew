package service;

import dao.AttendanceRecordDao;
import dao.impl.AttendanceRecordDaoImpl;
import model.AttendanceRecord;
import until.EmployeeMenu;
import until.ExportUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AttendanceService {
    private AttendanceRecordDao attendanceDao = new AttendanceRecordDaoImpl();
    private Scanner sc = new Scanner(System.in);
    
    public void checkIn(String employeeId) {
        if (attendanceDao.checkIn(employeeId)) {
            System.out.println("签到成功");
        } else {
            System.out.println("今日已签到或签到失败");
        }
        EmployeeMenu.showMenu(employeeId);
    }
    
    public void checkOut(String employeeId) {
        if (attendanceDao.checkOut(employeeId)) {
            System.out.println("签退成功");
        } else {
            System.out.println("今日未签到或签退失败");
        }
        EmployeeMenu.showMenu(employeeId);
    }
    
    public void viewAttendanceRecords(String employeeId) {
        System.out.println("请输入查询开始日期 (yyyy-MM-dd):");
        String startDateStr = sc.nextLine();
        System.out.println("请输入查询结束日期 (yyyy-MM-dd):");
        String endDateStr = sc.nextLine();
        
        try {
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);
            
            List<AttendanceRecord> records = attendanceDao.getAttendanceByDateRange(employeeId, startDate, endDate);
            
            System.out.println("考勤记录查询结果：");
            System.out.println("ID\t日期\t\t签到时间\t\t签退时间\t\t状态\t\t备注");
            for (AttendanceRecord record : records) {
                System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\n",
                        record.getRecordId(),
                        record.getCheckInTime().toLocalDate(),
                        record.getCheckInTime().toLocalTime(),
                        record.getCheckOutTime() != null ? record.getCheckOutTime().toLocalTime() : "未签退",
                        record.getAttendanceStatus(),
                        record.getRemarks() != null ? record.getRemarks() : "");
            }
            
            System.out.println("是否导出报表? (Y/N)");
            String choice = sc.nextLine();
            if ("Y".equalsIgnoreCase(choice)) {
                ExportUtil.exportAttendanceRecords(records, employeeId);
            }
            
        } catch (Exception e) {
            System.out.println("日期格式错误，请使用yyyy-MM-dd格式");
        }
        
        EmployeeMenu.showMenu(employeeId);
    }
    
    // 管理员审核异常考勤记录
    public void reviewAbnormalAttendance() {
        List<AttendanceRecord> abnormalRecords = attendanceDao.getAbnormalAttendance();
        
        if (abnormalRecords.isEmpty()) {
            System.out.println("没有异常考勤记录需要审核");
            return;
        }
        
        System.out.println("异常考勤记录列表：");
        System.out.println("ID\t员工ID\t日期\t\t签到时间\t\t签退时间\t\t状态\t\t备注");
        for (AttendanceRecord record : abnormalRecords) {
            System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\t%s\n",
                    record.getRecordId(),
                    record.getEmployeeId(),
                    record.getCheckInTime().toLocalDate(),
                    record.getCheckInTime().toLocalTime(),
                    record.getCheckOutTime() != null ? record.getCheckOutTime().toLocalTime() : "未签退",
                    record.getAttendanceStatus(),
                    record.getRemarks() != null ? record.getRemarks() : "");
        }
        
        System.out.println("请输入要审核的记录ID：");
        int recordId = Integer.parseInt(sc.nextLine());
        
        System.out.println("请输入审核状态 (NORMAL, LATE, EARLY_LEAVE, ABSENT)：");
        String status = sc.nextLine();
        
        System.out.println("请输入备注：");
        String remarks = sc.nextLine();
        
        if (attendanceDao.updateAttendanceStatus(recordId, status, remarks)) {
            System.out.println("审核成功");
        } else {
            System.out.println("审核失败");
        }
    }
}