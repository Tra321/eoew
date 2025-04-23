package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;
import model.Attendance;
import until.BaseDao;
import dao.AttendanceDao;

public class AttendanceDaoImpl implements AttendanceDao {

    @Override
    public void addAttendance(Attendance attendance) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BaseDao.getCon();
            String sql = "insert into attendance (studentID, attendanceDate, checkInTime, checkOutTime, status) values(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, attendance.getStudentID());
            ps.setDate(2, attendance.getAttendanceDate());
            ps.setTime(3, attendance.getCheckInTime());
            ps.setTime(4, attendance.getCheckOutTime());
            ps.setString(5, attendance.getStatus());
            int a = ps.executeUpdate();
            if(a > 0){
                System.out.println("添加考勤记录成功");
            } else {
                System.out.println("添加考勤记录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    public void deleteAttendance(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BaseDao.getCon();
            String sql = "delete from attendance where id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int a = ps.executeUpdate();
            if(a > 0){
                System.out.println("删除考勤记录成功");
            } else {
                System.out.println("删除考勤记录失败，请检查ID是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    public void updateAttendance(Attendance attendance) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BaseDao.getCon();
            String sql = "update attendance set studentID=?, attendanceDate=?, checkInTime=?, checkOutTime=?, status=? where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, attendance.getStudentID());
            ps.setDate(2, attendance.getAttendanceDate());
            ps.setTime(3, attendance.getCheckInTime());
            ps.setTime(4, attendance.getCheckOutTime());
            ps.setString(5, attendance.getStatus());
            ps.setInt(6, attendance.getId());
            int a = ps.executeUpdate();
            if(a > 0){
                System.out.println("更新考勤记录成功");
            } else {
                System.out.println("更新考勤记录失败，请检查 ID 是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    public Attendance selectOneAttendance(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select * from attendance where id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                Attendance attendance = new Attendance();
                attendance.setStudentID(rs.getString("studentID"));
                attendance.setAttendanceDate(rs.getDate("attendanceDate"));
                attendance.setCheckInTime(rs.getTime("checkInTime"));
                attendance.setCheckOutTime(rs.getTime("checkOutTime"));
                attendance.setStatus(rs.getString("status"));
                return attendance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
        return null;
    }

    @Override
    public void selectStudentAttendance(String studentID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select * from attendance where studentID=? order by attendanceDate desc";
            ps = con.prepareStatement(sql);
            ps.setString(1, studentID);
            rs = ps.executeQuery();
            System.out.println("日期\t\t签到时间\t签退时间\t状态");
            while(rs.next()){
                System.out.println(rs.getDate("attendanceDate") + "\t" + 
                                  rs.getTime("checkInTime") + "\t" + 
                                  rs.getTime("checkOutTime") + "\t" + 
                                  rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
    }

    @Override
    public void selectDateAttendance(Date date) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select a.*, s.studentName, s.classID from attendance a " +
                        "join student s on a.studentID = s.studentID " +
                        "where a.attendanceDate=? order by s.classID, s.studentID";
            ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            rs = ps.executeQuery();
            System.out.println("学号\t姓名\t班级\t签到时间\t签退时间\t状态");
            while(rs.next()){
                System.out.println(rs.getString("studentID") + "\t" + 
                                  rs.getString("studentName") + "\t" + 
                                  rs.getString("classID") + "\t" + 
                                  rs.getTime("checkInTime") + "\t" + 
                                  rs.getTime("checkOutTime") + "\t" + 
                                  rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
    }

    @Override
    public void selectClassAttendance(String classID, Date date) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select a.*, s.studentName from attendance a " +
                        "join student s on a.studentID = s.studentID " +
                        "where s.classID=? and a.attendanceDate=? " +
                        "order by s.studentID";
            ps = con.prepareStatement(sql);
            ps.setString(1, classID);
            ps.setDate(2, date);
            rs = ps.executeQuery();
            System.out.println("日期: " + date);
            System.out.println("班级: " + classID);
            System.out.println("学号\t姓名\t签到时间\t签退时间\t状态");
            while(rs.next()){
                System.out.println(rs.getString("studentID") + "\t" + 
                                  rs.getString("studentName") + "\t" + 
                                  rs.getTime("checkInTime") + "\t" + 
                                  rs.getTime("checkOutTime") + "\t" + 
                                  rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
    }

    @Override
    public void generateAttendanceReport(String studentID, Date startDate, Date endDate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select s.studentName, s.classID, " +
                        "count(case when a.status='正常' then 1 end) as normal_count, " +
                        "count(case when a.status='迟到' then 1 end) as late_count, " +
                        "count(case when a.status='缺勤' then 1 end) as absent_count, " +
                        "count(case when a.status='请假' then 1 end) as leave_count " +
                        "from attendance a " +
                        "join student s on a.studentID = s.studentID " +
                        "where a.studentID=? and a.attendanceDate between ? and ? " +
                        "group by s.studentName, s.classID";
            ps = con.prepareStatement(sql);
            ps.setString(1, studentID);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            rs = ps.executeQuery();
            
            if(rs.next()){
                System.out.println("学生考勤报表");
                System.out.println("时间段: " + startDate + " 至 " + endDate);
                System.out.println("学号: " + studentID);
                System.out.println("姓名: " + rs.getString("studentName"));
                System.out.println("班级: " + rs.getString("classID"));
                System.out.println("正常出勤: " + rs.getInt("normal_count") + " 次");
                System.out.println("迟到: " + rs.getInt("late_count") + " 次");
                System.out.println("缺勤: " + rs.getInt("absent_count") + " 次");
                System.out.println("请假: " + rs.getInt("leave_count") + " 次");
            } else {
                System.out.println("未找到该学生的考勤记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
    }
} 