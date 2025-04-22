package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.String;

import model.student;
import model.Live;
import model.Dormitory;
import until.*;
import dao.StudentDao;

public class StudentDaoImpl implements StudentDao{

    @Override
    //查询学生的学号以及密码
    public student login(student student) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con= BaseDao.getCon();
            String sql="select * from student where studentID=? and studentPassword=?";
            ps=con.prepareStatement(sql);
            /*ps.setString(1,student.getStudentName());*/
            ps.setString(1, student.getStudentID());
            ps.setString(2, student.getStudentPassword());
            rs=ps.executeQuery();
            student students= null;
            if(rs.next()){
                students =new student();
                //从数据库中获取值设置到实体类的setter方法中
                /*students.setStudentName(rs.getString("studentName"));*/
                students.setStudentID(rs.getString("studentID"));
                students.setStudentPassword(rs.getString("studentPassword"));
                return students;
            }else{
                return null;
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    //更新用户名以及密码
    public void changePass(student student) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql = "update student set studentPassword=? where studentID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, student.getStudentPassword());
            ps.setString(2, student.getStudentID());
            int a =ps.executeUpdate();

        }    catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    //宿舍管理
    public void chooseDormitory(String studentID,String dormitoryID,int dormitoryFloor) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql ="insert into live (studentID,dormitoryID,dormitoryFloor) values(?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1,studentID);
            ps.setString(2, dormitoryID);
            ps.setInt(3,dormitoryFloor);
            int a =ps.executeUpdate();
            if(a>0){
                System.out.println("宿舍管理成功");
            }
            else{
                System.out.println("输入ID有误，宿舍管理失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //删除宿舍管理
    @Override
    public void deleteDormitory(String studentID,String dormitoryID) {
        // TODO Auto-generated method stub

        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="delete from live where studentID=? and dormitoryID=? ";
            ps=con.prepareStatement(sql);
            ps.setString(1, studentID);
            ps.setString(2, dormitoryID);
            int a =ps.executeUpdate();
            if(a>0){
                System.out.println("删除成功");
            }
            else{
                System.out.println("输入ID有误，删除失败");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //查询已选宿舍
    @Override
    public void getStuDormitory(String studentID) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();

            String sql ="select * from dormitory where dormitoryID in (select dormitoryID from live where studentID=?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, studentID);
            rs=ps.executeQuery();
            System.out.println("dormitoryID \t dormitoryBuild \t dormitoryFloor \t");
            while (rs.next()) {
                //System.out.println(6);
                Dormitory dormitory = new Dormitory();
                dormitory.setDormitoryID(rs.getString("dormitoryID"));
                dormitory.setDormitoryBuild(rs.getString("dormitoryBuild"));
                dormitory.setDormitoryFloor(rs.getInt("dormitoryFloor"));

                System.out.println(dormitory.getDormitoryID()+" "+dormitory.getDormitoryBuild()+" "+dormitory.getDormitoryFloor());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    //查询获得楼层
    @Override
    public void getStuDormitoryFloor(String studentID) {
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql = "Select sum(dormitoryFloor) From dormitory,live Where dormitory.dormitoryID=live.dormitoryID and live.dormitoryFloor>60 and live.studentID=?;";
            ps=con.prepareStatement(sql);
            ps.setString(1, studentID);
            rs=ps.executeQuery();
            while (rs.next()) {
                Dormitory dormitory = new Dormitory();
                dormitory.setDormitoryFloor(rs.getInt("sum(dormitoryFloor)"));

                System.out.println(dormitory.getDormitoryFloor());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //查询可选宿舍
    @Override
    public void getAvaDormitory(String studentID) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql = "select * from dormitory where dormitoryID not in (select dormitoryID from live where studentID=?)";
            ps=con.prepareStatement(sql);
            ps.setString(1,studentID);
            rs=ps.executeQuery();
            System.out.println("dormitoryID \t dormitoryBuild \t dormitoryFloor \t");
            while (rs.next()) {
                Dormitory dormitory = new Dormitory();
                dormitory.setDormitoryID(rs.getString("dormitoryID"));
                dormitory.setDormitoryBuild(rs.getString("dormitoryBuild"));
                dormitory.setDormitoryFloor(rs.getInt("dormitoryFloor"));

                System.out.println(dormitory.getDormitoryID()+" "+dormitory.getDormitoryBuild()+" "+dormitory.getDormitoryFloor());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //打印楼层单
    @Override
    public void printStuDormitoryFloor(String studentID) {
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql = "select dormitoryBuild,dormitoryFloor from dormitory,live where live.studentID in (SELECT live.studentID from live,student where live.studentID = student.studentID and student.studentID = ?);";
            ps=con.prepareStatement(sql);
            ps.setString(1,studentID);
            rs=ps.executeQuery();
            System.out.println("dormitoryBuild \t dormitoryFloor \t ");
            while (rs.next()) {
                Dormitory dormitory = new Dormitory();
                Live live = new Live();
                dormitory.setDormitoryBuild(rs.getString("dormitoryBuild"));
                live.setDormitoryFloor(rs.getInt("dormitoryFloor"));

                System.out.println(dormitory.getDormitoryBuild()+"    \t  "+live.getDormitoryFloor());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}