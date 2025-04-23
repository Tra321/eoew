package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Class;
import until.BaseDao;
import dao.ClassDao;

public class ClassDaoImpl implements ClassDao {

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
            if(a > 0){
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
            if(a > 0){
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
            if(a > 0){
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
            if(rs.next()){
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
            while(rs.next()){
                System.out.println(rs.getString("classID") + "\t" + rs.getString("className"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.close(con, ps, rs);
        }
    }
} 