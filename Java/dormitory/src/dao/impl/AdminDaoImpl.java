package dao.impl;

import dao.AdminDao;
import model.Admin;
import until.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin login(Admin admin) {
        String sql = "SELECT * FROM admin WHERE admin_id = ? AND admin_password = ?";
        return BaseDao.executeQuery(rs -> {
            if (rs.next()) {
                return mapAdmin(rs);
            }
            return null;
        }, sql, admin.getAdminId(), admin.getAdminPassword());
    }

    @Override
    public boolean changePassword(String adminId, String newPassword) {
        String sql = "UPDATE admin SET admin_password = ? WHERE admin_id = ?";
        return BaseDao.executeUpdate(sql, newPassword, adminId) > 0;
    }

    @Override
    public Admin findById(String adminId) {
        String sql = "SELECT * FROM admin WHERE admin_id = ?";
        return BaseDao.executeQuery(rs -> {
            if (rs.next()) {
                return mapAdmin(rs);
            }
            return null;
        }, sql, adminId);
    }

    @Override
    public boolean add(Admin admin) {
        String sql = "INSERT INTO admin (admin_id, admin_name, admin_password, phone, email) " +
                     "VALUES (?, ?, ?, ?, ?)";
        return BaseDao.executeUpdate(sql, 
                admin.getAdminId(), 
                admin.getAdminName(), 
                admin.getAdminPassword(),
                admin.getPhone(),
                admin.getEmail()) > 0;
    }

    @Override
    public boolean update(Admin admin) {
        String sql = "UPDATE admin SET admin_name = ?, phone = ?, email = ? WHERE admin_id = ?";
        return BaseDao.executeUpdate(sql, 
                admin.getAdminName(),
                admin.getPhone(),
                admin.getEmail(),
                admin.getAdminId()) > 0;
    }

    @Override
    public boolean delete(String adminId) {
        String sql = "DELETE FROM admin WHERE admin_id = ?";
        return BaseDao.executeUpdate(sql, adminId) > 0;
    }

    @Override
    public List<Admin> findAll() {
        String sql = "SELECT * FROM admin";
        return BaseDao.executeQuery(rs -> {
            List<Admin> admins = new ArrayList<>();
            while (rs.next()) {
                admins.add(mapAdmin(rs));
            }
            return admins;
        }, sql);
    }
    
    /**
     * 从ResultSet映射为Admin对象
     */
    private Admin mapAdmin(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setAdminId(rs.getString("admin_id"));
        admin.setAdminName(rs.getString("admin_name"));
        admin.setAdminPassword(rs.getString("admin_password"));
        admin.setPhone(rs.getString("phone"));
        admin.setEmail(rs.getString("email"));
        return admin;
    }
} 