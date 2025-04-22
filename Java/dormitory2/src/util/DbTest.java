package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest {
    public static void main(String[] args) {
        try {
            // 测试数据库连接
            System.out.println("测试数据库连接...");
            Connection conn = DatabaseUtil.getConnection();
            System.out.println("成功连接到数据库！");
            
            // 测试查询
            System.out.println("测试查询users表...");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            if (rs.next()) {
                System.out.println("成功查询到用户: " + rs.getString("username"));
            } else {
                System.out.println("users表中没有数据");
            }
            
            // 测试查询accounts表
            System.out.println("测试查询accounts表...");
            rs = stmt.executeQuery("SELECT * FROM accounts");
            if (rs.next()) {
                System.out.println("成功查询到账号: " + rs.getString("account_name"));
            } else {
                System.out.println("accounts表中没有数据");
            }
            
            // 关闭连接
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("数据库测试完成！");
            
        } catch (Exception e) {
            System.out.println("发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 