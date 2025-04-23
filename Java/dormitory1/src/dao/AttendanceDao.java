package dao;

import java.sql.Date;
import model.Attendance;

public interface AttendanceDao {
    public void addAttendance(Attendance attendance); // 添加考勤记录
    public void deleteAttendance(int id); // 删除考勤记录
    public void updateAttendance(Attendance attendance); // 更新考勤记录
    public Attendance selectOneAttendance(int id); // 查询单条考勤记录
    public void selectStudentAttendance(String studentID); // 查询某学生的所有考勤记录
    public void selectDateAttendance(Date date); // 查询某日期的所有考勤记录
    public void selectClassAttendance(String classID, Date date); // 查询某班级某日期的考勤记录
    public void generateAttendanceReport(String studentID, Date startDate, Date endDate); // 生成考勤报表
} 