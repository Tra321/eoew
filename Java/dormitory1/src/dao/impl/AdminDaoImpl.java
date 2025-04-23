package dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

import dao.AdminDao;
import model.Class;
import model.admin;
import model.student;
import model.Attendance;
import until.BaseDao;

public class AdminDaoImpl implements AdminDao {

    //管理员登录
    @Override
    public admin login(admin admin) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select * from admin where adminID=? and adminPassword=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, admin.getAdminID());
            ps.setString(2, admin.getAdminPassword());
            rs = ps.executeQuery();
            admin admins = null;
            if (rs.next()) {
                admins = new admin();
                admins.setAdminID(rs.getString("adminID"));
                admins.setAdminPassword(rs.getString("adminPassword"));
                return admins;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
        return null;
    }

    // ----- 班级管理 -----
    
    @Override
    public void addClass(Class clazz) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BaseDao.getCon();
            String sql = "insert into class (classID, className) values(?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, clazz.getClassID());
            ps.setString(2, clazz.getClassName());
            int a = ps.executeUpdate();
            if (a > 0) {
                System.out.println("添加班级成功");
            } else {
                System.out.println("添加班级失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    public void deleteClass(String classID) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BaseDao.getCon();
            String sql = "delete from class where classID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, classID);
            int a = ps.executeUpdate();
            if (a > 0) {
                System.out.println("删除班级成功");
            } else {
                System.out.println("删除班级失败，请检查班级ID是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    public void updateClass(Class clazz) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BaseDao.getCon();
            String sql = "update class set className=? where classID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, clazz.getClassName());
            ps.setString(2, clazz.getClassID());
            int a = ps.executeUpdate();
            if (a > 0) {
                System.out.println("更新班级信息成功");
            } else {
                System.out.println("更新班级信息失败，请检查班级ID是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    public Class selectOneClass(String classID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select * from class where classID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, classID);
            rs = ps.executeQuery();
            if (rs.next()) {
                Class clazz = new Class();
                clazz.setClassID(rs.getString("classID"));
                clazz.setClassName(rs.getString("className"));
                return clazz;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
        return null;
    }

    @Override
    public void selectAllClass() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select * from class";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("班级ID\t班级名称");
            while (rs.next()) {
                System.out.println(rs.getString("classID") + "\t" + rs.getString("className"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
    }

    // ----- 学生管理 -----
    
    @Override
    public void addStudent(student student) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BaseDao.getCon();
            String sql = "insert into student (studentID, studentName, studentPassword, classID, studentTel, studentEmail) values(?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getStudentID());
            ps.setString(2, student.getStudentName());
            ps.setString(3, student.getStudentPassword());
            ps.setString(4, student.getClassID());
            ps.setString(5, student.getStudentTel());
            ps.setString(6, student.getStudentEmail());
            int a = ps.executeUpdate();
            if (a > 0) {
                System.out.println("添加学生成功");
            } else {
                System.out.println("添加学生失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    public void deleteStudent(String studentID) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BaseDao.getCon();
            String sql = "delete from student where studentID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, studentID);
            int a = ps.executeUpdate();
            if (a > 0) {
                System.out.println("删除学生成功");
                // 同时删除该学生的考勤记录
                String sql2 = "delete from attendance where studentID=?";
                ps = con.prepareStatement(sql2);
                ps.setString(1, studentID);
                ps.executeUpdate();
            } else {
                System.out.println("删除学生失败，请检查学生ID是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    public void updateStudent(student student) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BaseDao.getCon();
            String sql = "update student set studentName=?, studentPassword=?, classID=?, studentTel=?, studentEmail=? where studentID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getStudentPassword());
            ps.setString(3, student.getClassID());
            ps.setString(4, student.getStudentTel());
            ps.setString(5, student.getStudentEmail());
            ps.setString(6, student.getStudentID());
            int a = ps.executeUpdate();
            if (a > 0) {
                System.out.println("更新学生信息成功");
            } else {
                System.out.println("更新学生信息失败，请检查学生ID是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    public student selectOneStudent(String studentID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select * from student where studentID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, studentID);
            rs = ps.executeQuery();
            if (rs.next()) {
                student student = new student();
                student.setStudentID(rs.getString("studentID"));
                student.setStudentName(rs.getString("studentName"));
                student.setStudentPassword(rs.getString("studentPassword"));
                student.setClassID(rs.getString("classID"));
                student.setStudentTel(rs.getString("studentTel"));
                student.setStudentEmail(rs.getString("studentEmail"));
                return student;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
        return null;
    }

    @Override
    public void selectAllStudent() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select s.*, c.className from student s left join class c on s.classID = c.classID order by s.classID, s.studentID";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("学号\t姓名\t班级\t电话\t邮箱");
            while (rs.next()) {
                System.out.println(rs.getString("studentID") + "\t" + 
                                   rs.getString("studentName") + "\t" + 
                                   rs.getString("className") + "\t" + 
                                   rs.getString("studentTel") + "\t" + 
                                   rs.getString("studentEmail"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
    }

    // ----- 考勤管理 -----
    
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
            if (a > 0) {
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
            if (a > 0) {
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
            if (a > 0) {
                System.out.println("更新考勤记录成功");
            } else {
                System.out.println("更新考勤记录失败，请检查ID是否正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    public void selectStudentAttendance(String studentID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select a.*, s.studentName from attendance a " +
                        "join student s on a.studentID = s.studentID " +
                        "where a.studentID=? order by a.attendanceDate desc";
            ps = con.prepareStatement(sql);
            ps.setString(1, studentID);
            rs = ps.executeQuery();
            System.out.println("学号: " + studentID);
            if (rs.next()) {
                System.out.println("姓名: " + rs.getString("studentName"));
                System.out.println("日期\t\t签到时间\t签退时间\t状态");
                do {
                    System.out.println(rs.getDate("attendanceDate") + "\t" + 
                                      rs.getTime("checkInTime") + "\t" + 
                                      rs.getTime("checkOutTime") + "\t" + 
                                      rs.getString("status"));
                } while (rs.next());
            } else {
                System.out.println("未找到该学生的考勤记录");
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
            String sql = "select a.*, s.studentName, s.classID, c.className from attendance a " +
                        "join student s on a.studentID = s.studentID " +
                        "left join class c on s.classID = c.classID " +
                        "where a.attendanceDate=? order by s.classID, s.studentID";
            ps = con.prepareStatement(sql);
            ps.setDate(1, date);
            rs = ps.executeQuery();
            System.out.println("日期: " + date);
            System.out.println("学号\t姓名\t班级\t签到时间\t签退时间\t状态");
            while (rs.next()) {
                System.out.println(rs.getString("studentID") + "\t" + 
                                  rs.getString("studentName") + "\t" + 
                                  rs.getString("className") + "\t" + 
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
            String sql = "select a.*, s.studentName, c.className from attendance a " +
                        "join student s on a.studentID = s.studentID " +
                        "join class c on s.classID = c.classID " +
                        "where s.classID=? and a.attendanceDate=? " +
                        "order by s.studentID";
            ps = con.prepareStatement(sql);
            ps.setString(1, classID);
            ps.setDate(2, date);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                System.out.println("日期: " + date);
                System.out.println("班级: " + rs.getString("className") + "(" + classID + ")");
                System.out.println("学号\t姓名\t签到时间\t签退时间\t状态");
                do {
                    System.out.println(rs.getString("studentID") + "\t" + 
                                      rs.getString("studentName") + "\t" + 
                                      rs.getTime("checkInTime") + "\t" + 
                                      rs.getTime("checkOutTime") + "\t" + 
                                      rs.getString("status"));
                } while (rs.next());
            } else {
                System.out.println("未找到该班级在" + date + "的考勤记录");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
    }

    @Override
    public void generateAttendanceStatistics(Date startDate, Date endDate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select c.classID, c.className, " +
                        "count(distinct s.studentID) as total_students, " +
                        "count(distinct case when a.status='正常' then a.studentID end) as normal_students, " +
                        "count(case when a.status='正常' then 1 end) as normal_count, " +
                        "count(case when a.status='迟到' then 1 end) as late_count, " +
                        "count(case when a.status='缺勤' then 1 end) as absent_count, " +
                        "count(case when a.status='请假' then 1 end) as leave_count " +
                        "from class c " +
                        "left join student s on c.classID = s.classID " +
                        "left join attendance a on s.studentID = a.studentID and a.attendanceDate between ? and ? " +
                        "group by c.classID, c.className " +
                        "order by c.classID";
            ps = con.prepareStatement(sql);
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            rs = ps.executeQuery();
            
            System.out.println("考勤统计报表");
            System.out.println("时间段: " + startDate + " 至 " + endDate);
            System.out.println("班级ID\t班级名称\t总人数\t正常出勤人数\t正常出勤次数\t迟到次数\t缺勤次数\t请假次数");
            while (rs.next()) {
                System.out.println(rs.getString("classID") + "\t" + 
                                  rs.getString("className") + "\t" + 
                                  rs.getInt("total_students") + "\t" + 
                                  rs.getInt("normal_students") + "\t" + 
                                  rs.getInt("normal_count") + "\t" + 
                                  rs.getInt("late_count") + "\t" + 
                                  rs.getInt("absent_count") + "\t" + 
                                  rs.getInt("leave_count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
    }
}
