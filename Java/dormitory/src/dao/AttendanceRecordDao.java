package dao;

import model.AttendanceRecord;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceRecordDao {
    boolean checkIn(String employeeId);
    boolean checkOut(String employeeId);
    AttendanceRecord getAttendanceByDateAndEmployee(String employeeId, LocalDate date);
    List<AttendanceRecord> getAttendanceByDateRange(String employeeId, LocalDate startDate, LocalDate endDate);
    List<AttendanceRecord> getAbnormalAttendance();
    boolean updateAttendanceStatus(int recordId, String status, String remarks);
}