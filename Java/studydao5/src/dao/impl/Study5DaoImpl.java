package dao.impl;

import dao.Study5Dao;
import until.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Study5DaoImpl implements Study5Dao {
    @Override
    public void addCourse(String courseID, String courseName, float courseCredit) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con= BaseDao.getCon();
            String sql="insert into course (courseID,courseName,courseCredit) values(?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, courseID);
            ps.setString(2, courseName);
            ps.setFloat(3, courseCredit);
            int a = ps.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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

    @Override
    public void updateCourse(String courseID, String courseName, float courseCredit) {
        {
            // TODO Auto-generated method stub
            Connection con = null;
            PreparedStatement ps= null;
            ResultSet rs=null;
            try {
                con=BaseDao.getCon();
                String sql="update course set courseCredit=?,courseName=? where courseID=?";
                ps=con.prepareStatement(sql);
                ps.setString(3, courseID);
                ps.setString(2, courseName);
                ps.setFloat(1, courseCredit);
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
    }

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

    @Override
    public void selectAllCourse() {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql ="select * from course ";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            System.out.println("courseID "+"\t"+" courseName"+"\t"+"courseCredit"+"\t");
            while(rs.next()){
                String courseID = rs.getString("courseID");
                String courseName = rs.getString("courseName");
                String courseCredit = rs.getString("courseCredit");
                System.out.println(courseID+" \t "+courseName+" \t "+courseCredit);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
