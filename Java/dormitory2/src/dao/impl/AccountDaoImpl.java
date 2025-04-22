package dao.impl;

import dao.AccountDao;
import model.Account;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    @Override
    public Account findById(int id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractAccountFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> findByUserId(int userId) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                accounts.add(extractAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public List<Account> searchByAccountName(int userId, String keyword) {
        List<Account> results = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE user_id = ? AND account_name LIKE ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(extractAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<Account> searchByCategory(int userId, String keyword) {
        List<Account> results = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE user_id = ? AND category LIKE ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(extractAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<Account> searchByDescription(int userId, String keyword) {
        List<Account> results = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE user_id = ? AND description LIKE ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(extractAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public boolean save(Account account) {
        String sql = "INSERT INTO accounts (account_name, username, password, category, description, user_id, create_time) VALUES (?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            System.out.println("准备保存账号: " + account.getAccountName());
            
            stmt.setString(1, account.getAccountName());
            stmt.setString(2, account.getUsername());
            stmt.setString(3, account.getPassword());
            stmt.setString(4, account.getCategory());
            stmt.setString(5, account.getDescription());
            stmt.setInt(6, account.getUserId());
            
            int affectedRows = stmt.executeUpdate();
            System.out.println("影响的行数: " + affectedRows);
            
            if (affectedRows == 0) {
                System.out.println("保存账号失败：没有行被插入");
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getInt(1));
                    System.out.println("保存账号成功，ID: " + account.getId());
                    return true;
                } else {
                    System.out.println("保存账号失败：无法获取ID");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("保存账号时发生SQL异常: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Account account) {
        String sql = "UPDATE accounts SET account_name = ?, username = ?, password = ?, category = ?, description = ? WHERE id = ? AND user_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getAccountName());
            stmt.setString(2, account.getUsername());
            stmt.setString(3, account.getPassword());
            stmt.setString(4, account.getCategory());
            stmt.setString(5, account.getDescription());
            stmt.setInt(6, account.getId());
            stmt.setInt(7, account.getUserId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM accounts WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Account extractAccountFromResultSet(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setAccountName(rs.getString("account_name"));
        account.setUsername(rs.getString("username"));
        account.setPassword(rs.getString("password"));
        account.setCategory(rs.getString("category"));
        account.setDescription(rs.getString("description"));
        account.setUserId(rs.getInt("user_id"));
        account.setCreateTime(rs.getString("create_time"));
        account.setUpdateTime(rs.getString("update_time"));
        return account;
    }
} 