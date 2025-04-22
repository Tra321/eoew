package dao.impl;

import dao.CourseDao;
import model.course;
import until.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao{

    //添加课程
    @Override
    public void addCourse(course coursebean) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="insert into course (courseID,courseName,courseCredit) values(?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, coursebean.getCourseID());
            ps.setString(2, coursebean.getCourseName());
            ps.setFloat(3, coursebean.getCourseCredit());
            int a = ps.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //删除课程
    @Override
    public void deleteCourse(String courseID) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql = "delete from course where courseID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, courseID);
            int a=ps.executeUpdate();
            if(a>0){
                System.out.println("删除成功");
                String sql2="delete from choice where courseID=?";
                ps=con.prepareStatement(sql2);
                ps.setString(1, courseID);
                int b=ps.executeUpdate();
            }
            else{
                System.out.println("输入ID有误，删除失败");
            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //修改课程
    @Override
    public void updateCourse(course coursebean) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="update course set courseCredit=?,courseName=? where courseID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, coursebean.getCourseID());
            ps.setString(2, coursebean.getCourseName());
            ps.setFloat(3, coursebean.getCourseCredit());
            int a = ps.executeUpdate();
            if(a>0){
                System.out.println("课程修改成功");
            }
            else{
                System.out.println("输入ID有误，课程修改失败");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //    查看某一课程
    @Override
    public void selectOneCourse(String courseID) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql ="select * from course where courseID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, courseID);
            rs=ps.executeQuery();
            if(rs.next()){
                String courseName = rs.getString("courseName");
                String courseCredit = rs.getString("courseCredit");
                System.out.println("courseID "+"\t"+" courseName"+"\t"+"courseCredit"+"\t");
                System.out.println(courseID+" \t "+courseName+" \t "+courseCredit);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //查看全部课程
    @Override
    public List<course> selectAllCourse() {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        List<course> courseslist = new ArrayList<>();
        try {
            con=BaseDao.getCon();
            String sql ="select * from course ";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();

            while(rs.next()){
                course coursebean = new course();
                coursebean.setCourseID(rs.getString("courseID"));
                coursebean.setCourseName(rs.getString("courseName"));
                coursebean.setCourseCredit(rs.getFloat("courseCredit"));
                courseslist.add(coursebean);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
