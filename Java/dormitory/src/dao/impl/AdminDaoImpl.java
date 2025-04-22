package dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.AdminDao;
import model.Dormitory;
import model.admin;
import model.student;
import model.Manager;
import until.BaseDao;

public class AdminDaoImpl implements AdminDao{

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
            if(rs.next()) {
                admins = new admin();
                admins.setAdminID(rs.getString("adminID"));
                admins.setAdminPassword(rs.getString("adminPassword"));
                return admins;
            }
            else {
                return null;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } finally {
            // 应添加资源关闭代码
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(ps != null) ps.close(); } catch(Exception e) {}
            try { if(con != null) con.close(); } catch(Exception e) {}
        }
    }
}
