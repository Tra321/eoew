package dao;

import model.Class;
import model.admin;
import model.student;
import model.Attendance;
import java.sql.Date;

public interface AdminDao {
    public admin login(admin admin); // 管理员登录
    
    // 班级管理
    public void addClass(Class clazz); // 添加班级
    public void deleteClass(String classID); // 删除班级
    public void updateClass(Class clazz); // 更新班级信息
    public Class selectOneClass(String classID); // 查询一个班级信息
    public void selectAllClass(); // 查询所有班级信息
    
    // 学生管理
    public void addStudent(student student); // 添加学生信息
    public void deleteStudent(String studentID); // 删除学生信息
    public void updateStudent(student student); // 更新学生信息
    public student selectOneStudent(String studentID); // 查询一个学生信息
    public void selectAllStudent(); // 查询所有学生信息
    
    // 考勤管理
    public void addAttendance(Attendance attendance); // 添加考勤记录
    public void deleteAttendance(int id); // 删除考勤记录
    public void updateAttendance(Attendance attendance); // 更新考勤记录
    public void selectStudentAttendance(String studentID); // 查询某学生的所有考勤记录
    public void selectDateAttendance(Date date); // 查询某日期的所有考勤记录
    public void selectClassAttendance(String classID, Date date); // 查询某班级某日期的考勤记录
    public void generateAttendanceStatistics(Date startDate, Date endDate); // 生成考勤统计
}
