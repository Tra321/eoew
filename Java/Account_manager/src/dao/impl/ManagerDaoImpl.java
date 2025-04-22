package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.String;

import model.*;
import until.*;
import dao.ManagerDao;

public class ManagerDaoImpl implements ManagerDao {

    @Override
    //查询宿管的工号以及密码
    public Manager login(Manager manager) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select * from manager where managerID=? and managerPassword=?";
            ps = con.prepareStatement(sql);
            /*ps.setString(1,student.getStudentName());*/
            ps.setString(1, manager.getManagerID());
            ps.setString(2, manager.getManagerPassword());
            rs = ps.executeQuery();
            Manager managers = null;
            if (rs.next()) {
                managers = new Manager();
                //从数据库中获取值设置到实体类的setter方法中
                /*managers.setmanagerName(rs.getString("managerName"));*/
                managers.setManagerID(rs.getString("managerID"));
                managers.setManagerPassword(rs.getString("managerPassword"));
                return managers;
            } else {
                return null;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    //更新用户名以及密码
    public void changePass(Manager manager) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "update manager set managerPassword=? where managerID=?";
            ps = con.prepareStatement(sql);

            ps.setString(1, manager.getManagerPassword());
            ps.setString(2, manager.getManagerID());
            int a = ps.executeUpdate();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    //录入自己所教宿舍的学生楼层
    public void getLiveDormitoryFloor(String managerID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql1 = "SELECT live.studentID,live.dormitoryFloor from live,manage where manage.dormitoryID = live.dormitoryID and managerID=?";
            ps = con.prepareStatement(sql1);
            ps.setString(1, managerID);
            rs = ps.executeQuery();
            System.out.println("studentID \t dormitoryFloor \t ");
            while (rs.next()) {
                Live live = new Live();
                live.setStudentID(rs.getString("studentID"));
                live.setDormitoryFloor(rs.getInt("dormitoryFloor"));

                System.out.println(live.getStudentID()+" \t "+ live.getDormitoryFloor());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
            /*String sql2 = "Insert into SELECT live.studentID,live.dormitoryFloor from live,manage where manage.dormitoryID = live.dormitoryID and managerID=? (studentID,dormitoryFloor) values(?,?)";
            int a = ps.executeUpdate();
            if (a > 0) {
                System.out.println("楼层录入成功");
            } else {
                System.out.println("楼层录入失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

    @Override
    //输出所教宿舍的楼层单
    public void getAllDormitoryFloor(String managerID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = BaseDao.getCon();
            String sql = "select live.dormitoryID,student.studentName,live.dormitoryFloor from student,live,manage where live.studentID in (select live.studentID from live,manage where live.dormitoryID=manage.dormitoryID and manage.managerID =?);";
            ps=con.prepareStatement(sql);
            ps.setString(1, managerID);
           /* ps.setString(1, studentID);*/
            rs = ps.executeQuery();
            System.out.println("dormitoryID \t studentName \t dormitoryFloor \t");
            while (rs.next()) {
                //System.out.println(6);
                Dormitory dormitory = new Dormitory();
                dormitory.setDormitoryID(rs.getString("dormitoryID"));
                dormitory.setDormitoryBuild(rs.getString("studentName"));
                dormitory.setDormitoryFloor(rs.getInt("dormitoryFloor"));

                System.out.println(dormitory.getDormitoryID() + " " + dormitory.getDormitoryBuild() + " " + dormitory.getDormitoryFloor());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

