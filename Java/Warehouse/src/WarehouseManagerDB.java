import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import until.DBUtil;

/**
 * 仓库管理器类 - 数据库版
 * 使用MySQL数据库存储物品信息
 */
public class WarehouseManagerDB {
    
    /**
     * 添加物品到仓库
     */
    public void addItem(WarehouseItem item) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DBUtil.getConnection();
            
            // 检查是否已存在相同ID的物品
            String checkSql = "SELECT * FROM items WHERE id = ?";
            ps = conn.prepareStatement(checkSql);
            ps.setString(1, item.getId());
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                System.out.println("ID为 " + item.getId() + " 的物品已存在，无法添加！");
                return;
            }
            
            // 添加新物品
            String sql = "INSERT INTO items (id, name, category, price, quantity) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getCategory());
            ps.setDouble(4, item.getPrice());
            ps.setInt(5, item.getQuantity());
            
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("物品添加成功！");
            } else {
                System.out.println("物品添加失败！");
            }
            
        } catch (SQLException e) {
            System.out.println("添加物品时出错: " + e.getMessage());
        } finally {
            closeResources(conn, ps);
        }
    }
    
    /**
     * 查找物品
     */
    public WarehouseItem findItem(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM items WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                
                return new WarehouseItem(id, name, category, price, quantity);
            }
        } catch (SQLException e) {
            System.out.println("查找物品时出错: " + e.getMessage());
        } finally {
            closeResources(conn, ps, rs);
        }
        
        return null;
    }
    
    /**
     * 删除物品
     */
    public boolean removeItem(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM items WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("删除物品时出错: " + e.getMessage());
        } finally {
            closeResources(conn, ps);
        }
        
        return false;
    }
    
    /**
     * 更新物品数量
     */
    public boolean updateItemQuantity(String id, int quantity) {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE items SET quantity = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setString(2, id);
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("更新物品数量时出错: " + e.getMessage());
        } finally {
            closeResources(conn, ps);
        }
        
        return false;
    }
    
    /**
     * 显示所有物品
     */
    public void showAllItems() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM items";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            boolean hasItems = false;
            
            System.out.println("\n=== 仓库物品列表 ===");
            System.out.printf("%-10s %-20s %-15s %-10s %-10s\n", 
                    "ID", "名称", "类别", "价格", "数量");
            System.out.println("-------------------------------------------------------------------------");
            
            while (rs.next()) {
                hasItems = true;
                String id = rs.getString("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                
                WarehouseItem item = new WarehouseItem(id, name, category, price, quantity);
                System.out.println(item);
            }
            
            if (!hasItems) {
                System.out.println("仓库中没有物品！");
            }
        } catch (SQLException e) {
            System.out.println("查询所有物品时出错: " + e.getMessage());
        } finally {
            closeResources(conn, ps, rs);
        }
    }
    
    /**
     * 显示单个物品
     */
    public void displayItem(WarehouseItem item) {
        System.out.printf("%-10s %-20s %-15s %-10s %-10s\n", 
                "ID", "名称", "类别", "价格", "数量");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(item);
    }
    
    /**
     * 按名称搜索物品
     */
    public void searchItemsByName(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM items WHERE name LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            
            List<WarehouseItem> results = new ArrayList<>();
            
            while (rs.next()) {
                String id = rs.getString("id");
                String itemName = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                
                results.add(new WarehouseItem(id, itemName, category, price, quantity));
            }
            
            if (results.isEmpty()) {
                System.out.println("未找到名称包含 '" + name + "' 的物品！");
            } else {
                System.out.println("\n=== 查询结果 ===");
                System.out.printf("%-10s %-20s %-15s %-10s %-10s\n", 
                        "ID", "名称", "类别", "价格", "数量");
                System.out.println("-------------------------------------------------------------------------");
                
                for (WarehouseItem item : results) {
                    System.out.println(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("按名称搜索物品时出错: " + e.getMessage());
        } finally {
            closeResources(conn, ps, rs);
        }
    }
    
    /**
     * 按类别搜索物品
     */
    public void searchItemsByCategory(String category) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM items WHERE category LIKE ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + category + "%");
            rs = ps.executeQuery();
            
            List<WarehouseItem> results = new ArrayList<>();
            
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String itemCategory = rs.getString("category");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                
                results.add(new WarehouseItem(id, name, itemCategory, price, quantity));
            }
            
            if (results.isEmpty()) {
                System.out.println("未找到类别包含 '" + category + "' 的物品！");
            } else {
                System.out.println("\n=== 查询结果 ===");
                System.out.printf("%-10s %-20s %-15s %-10s %-10s\n", 
                        "ID", "名称", "类别", "价格", "数量");
                System.out.println("-------------------------------------------------------------------------");
                
                for (WarehouseItem item : results) {
                    System.out.println(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("按类别搜索物品时出错: " + e.getMessage());
        } finally {
            closeResources(conn, ps, rs);
        }
    }
    
    /**
     * 关闭数据库资源
     */
    private void closeResources(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            System.out.println("关闭PreparedStatement时出错: " + e.getMessage());
        }
        
        DBUtil.closeConnection(conn);
    }
    
    /**
     * 关闭数据库资源（包括ResultSet）
     */
    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.out.println("关闭ResultSet时出错: " + e.getMessage());
        }
        
        closeResources(conn, ps);
    }
} 