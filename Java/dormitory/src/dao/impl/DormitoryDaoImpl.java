package dao.impl;

import dao.DormitoryDao;
import until.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DormitoryDaoImpl implements DormitoryDao{

    //添加宿舍
    @Override
    public void addDormitory(String dormitoryID, String dormitoryBuild, float dormitoryFloor) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="insert into dormitory (dormitoryID,dormitoryBuild,dormitoryFloor) values(?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, dormitoryID);
            ps.setString(2, dormitoryBuild);
            ps.setFloat(3, dormitoryFloor);
            int a = ps.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //删除宿舍
    @Override
    public void deleteDormitory(String dormitoryID) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql = "delete from dormitory where dormitoryID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, dormitoryID);
            int a=ps.executeUpdate();
            if(a>0){
                System.out.println("删除成功");
                String sql2="delete from choice where dormitoryID=?";
                ps=con.prepareStatement(sql2);
                ps.setString(1, dormitoryID);
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
    //修改宿舍
    @Override
    public void updateDormitory(String dormitoryID, String dormitoryBuild, float dormitoryFloor) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="update dormitory set dormitoryID=?,dormitoryBuild=? where dormitoryFloor=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, dormitoryID);
            ps.setString(2, dormitoryBuild);
            ps.setFloat(3, dormitoryFloor);
            int a = ps.executeUpdate();
            if(a>0){
                System.out.println("宿舍修改成功");
            }
            else{
                System.out.println("输入ID有误，宿舍修改失败");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //    查看某一宿舍
    @Override
    public void selectOneDormitory(String dormitoryID) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql ="select * from dormitory where dormitoryID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, dormitoryID);
            rs=ps.executeQuery();
            if(rs.next()){
                String dormitoryBuild = rs.getString("dormitoryBuild");
                String dormitoryFloor = rs.getString("dormitoryFloor");
                System.out.println("dormitoryID "+"\t"+" dormitoryBuild"+"\t"+"dormitoryFloor"+"\t");
                System.out.println(dormitoryID+" \t "+dormitoryBuild+" \t "+dormitoryFloor);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //查看全部宿舍
    @Override
    public void selectAllDormitory() {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql ="select * from dormitory ";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            System.out.println("dormitoryID "+"\t"+" dormitoryBuild"+"\t"+"dormitoryFloor"+"\t");
            while(rs.next()){
                String dormitoryID = rs.getString("dormitoryID");
                String dormitoryBuild = rs.getString("dormitoryBuild");
                String dormitoryFloor = rs.getString("dormitoryFloor");
                System.out.println(dormitoryID+" \t "+dormitoryBuild+" \t "+dormitoryFloor);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
