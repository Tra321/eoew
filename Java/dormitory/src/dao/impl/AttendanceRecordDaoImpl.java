package dao.impl;

import dao.AttendanceRecordDao;
import model.AttendanceRecord;
import until.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRecordDaoImpl implements AttendanceRecordDao {

    @Override
    public boolean checkIn(String employeeId) {
        // 检查今天是否已签到
        LocalDate today = LocalDate.now();
        AttendanceRecord record = getAttendanceByDateAndEmployee(employeeId, today);
        
        if (record != null) {
            return false; // 今天已经签到
        }
        
        // 创建新的签到记录
        String sql = "INSERT INTO attendance_record (employee_id, check_in_time, attendance_status) VALUES (?, ?, ?)";
        LocalDateTime now = LocalDateTime.now();
        
        // 判断是否迟到（假设9:00为上班时间）
        LocalTime startTime = LocalTime.of(9, 0);
        String status = "NORMAL";
        
        if (now.toLocalTime().isAfter(startTime)) {
            status = "LATE";
        }
        
        return BaseDao.executeUpdate(sql, employeeId, now, status) > 0;
    }

    @Override
    public boolean checkOut(String employeeId) {
        // 查找今天的签到记录
        LocalDate today = LocalDate.now();
        AttendanceRecord record = getAttendanceByDateAndEmployee(employeeId, today);
        
        if (record == null || record.getCheckOutTime() != null) {
            return false; // 今天未签到或已签退
        }
        
        // 更新签退时间
        String sql = "UPDATE attendance_record SET check_out_time = ? WHERE record_id = ?";
        LocalDateTime now = LocalDateTime.now();
        
        // 判断是否早退（假设18:00为下班时间）
        LocalTime endTime = LocalTime.of(18, 0);
        
        if (now.toLocalTime().isBefore(endTime)) {
            // 更新考勤状态为早退
            sql = "UPDATE attendance_record SET check_out_time = ?, attendance_status = ? WHERE record_id = ?";
            return BaseDao.executeUpdate(sql, now, "EARLY_LEAVE", record.getRecordId()) > 0;
        } else {
            return BaseDao.executeUpdate(sql, now, record.getRecordId()) > 0;
        }
    }

    @Override
    public AttendanceRecord getAttendanceByDateAndEmployee(String employeeId, LocalDate date) {
        String sql = "SELECT * FROM attendance_record WHERE employee_id = ? AND DATE(check_in_time) = ?";
        return BaseDao.executeQuery(rs -> {
            if (rs.next()) {
                return mapAttendanceRecord(rs);
            }
            return null;
        }, sql, employeeId, date);
    }

    @Override
    public List<AttendanceRecord> getAttendanceByDateRange(String employeeId, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT * FROM attendance_record WHERE employee_id = ? AND DATE(check_in_time) BETWEEN ? AND ? ORDER BY check_in_time DESC";
        return BaseDao.executeQuery(rs -> {
            List<AttendanceRecord> records = new ArrayList<>();
            while (rs.next()) {
                records.add(mapAttendanceRecord(rs));
            }
            return records;
        }, sql, employeeId, startDate, endDate);
    }

    @Override
    public List<AttendanceRecord> getAbnormalAttendance() {
        String sql = "SELECT * FROM attendance_record WHERE attendance_status IN ('LATE', 'EARLY_LEAVE', 'ABSENT') ORDER BY check_in_time DESC";
        return BaseDao.executeQuery(rs -> {
            List<AttendanceRecord> records = new ArrayList<>();
            while (rs.next()) {
                records.add(mapAttendanceRecord(rs));
            }
            return records;
        }, sql);
    }

    @Override
    public boolean updateAttendanceStatus(int recordId, String status, String remarks) {
        String sql = "UPDATE attendance_record SET attendance_status = ?, remarks = ? WHERE record_id = ?";
        return BaseDao.executeUpdate(sql, status, remarks, recordId) > 0;
    }
    
    /**
     * 从ResultSet映射为AttendanceRecord对象
     */
    private AttendanceRecord mapAttendanceRecord(ResultSet rs) throws SQLException {
        AttendanceRecord record = new AttendanceRecord();
        record.setRecordId(rs.getInt("record_id"));
        record.setEmployeeId(rs.getString("employee_id"));
        
        // 处理时间
        java.sql.Timestamp checkInTimestamp = rs.getTimestamp("check_in_time");
        if (checkInTimestamp != null) {
            record.setCheckInTime(checkInTimestamp.toLocalDateTime());
        }
        
        java.sql.Timestamp checkOutTimestamp = rs.getTimestamp("check_out_time");
        if (checkOutTimestamp != null) {
            record.setCheckOutTime(checkOutTimestamp.toLocalDateTime());
        }
        
        record.setAttendanceStatus(rs.getString("attendance_status"));
        record.setRemarks(rs.getString("remarks"));
        
        return record;
    }
} 