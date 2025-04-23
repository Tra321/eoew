package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.String;

import model.student;
import until.*;
import dao.StudentDao;

public class StudentDaoImpl implements StudentDao {

    @Override
    //查询学生的学号以及密码
    public student login(student student) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select * from student where studentID=? and studentPassword=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getStudentID());
            ps.setString(2, student.getStudentPassword());
            rs = ps.executeQuery();
            student students = null;
            if (rs.next()) {
                students = new student();
                students.setStudentID(rs.getString("studentID"));
                students.setStudentName(rs.getString("studentName"));
                students.setStudentPassword(rs.getString("studentPassword"));
                students.setClassID(rs.getString("classID"));
                students.setStudentTel(rs.getString("studentTel"));
                students.setStudentEmail(rs.getString("studentEmail"));
                return students;
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

    @Override
    //修改密码
    public void changePass(student student) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BaseDao.getCon();
            String sql = "update student set studentPassword=? where studentID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getStudentPassword());
            ps.setString(2, student.getStudentID());
            int a = ps.executeUpdate();
            if (a > 0) {
                System.out.println("密码修改成功");
            } else {
                System.out.println("密码修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    //更新个人信息
    public void updateProfile(student student) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = BaseDao.getCon();
            String sql = "update student set studentName=?, studentTel=?, studentEmail=? where studentID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getStudentTel());
            ps.setString(3, student.getStudentEmail());
            ps.setString(4, student.getStudentID());
            int a = ps.executeUpdate();
            if (a > 0) {
                System.out.println("个人信息更新成功");
            } else {
                System.out.println("个人信息更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, null);
        }
    }

    @Override
    //获取学生信息
    public student getStudentInfo(String studentID) {
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
    //获取班级所有学生
    public void getStudentsByClass(String classID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select * from student where classID=? order by studentID";
            ps = con.prepareStatement(sql);
            ps.setString(1, classID);
            rs = ps.executeQuery();
            System.out.println("学号\t姓名\t联系电话\t邮箱");
            while (rs.next()) {
                System.out.println(rs.getString("studentID") + "\t" + 
                                  rs.getString("studentName") + "\t" + 
                                  rs.getString("studentTel") + "\t" + 
                                  rs.getString("studentEmail"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
    }

    @Override
    //获取所有学生
    public void getAllStudents() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select s.*, c.className from student s left join class c on s.classID = c.classID order by s.classID, s.studentID";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("学号\t姓名\t班级\t联系电话\t邮箱");
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
}