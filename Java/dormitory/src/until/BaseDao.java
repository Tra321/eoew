package until;

import java.sql.*;

/**
 * 数据库操作基础类，提供连接管理和基本CRUD方法
 */
public class BaseDao {
    // 数据库连接信息
    private static final String URL = "jdbc:mysql://localhost:3306/attendance_system?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    static {
        try {
            // 加载MySQL驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("加载数据库驱动失败！", e);
        }
    }
    
    /**
     * 获取数据库连接
     * @return 数据库连接对象
     * @throws SQLException 如果获取连接失败
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    /**
     * 关闭数据库连接资源
     * @param conn 连接对象
     * @param stmt Statement对象
     * @param rs ResultSet对象
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 执行更新操作（INSERT、UPDATE、DELETE）
     * @param sql SQL语句
     * @param params 参数数组
     * @return 受影响的行数
     */
    public static int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            
            // 设置参数
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, null);
        }
        
        return rows;
    }
    
    /**
     * 执行查询操作
     * @param processor 结果处理器接口
     * @param sql SQL语句
     * @param params 参数数组
     * @return 处理后的结果
     */
    public static <T> T executeQuery(ResultSetProcessor<T> processor, String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        T result = null;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            
            // 设置参数
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            
            rs = pstmt.executeQuery();
            result = processor.process(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pstmt, rs);
        }
        
        return result;
    }
    
    /**
     * 结果集处理器接口
     * @param <T> 返回结果类型
     */
    public interface ResultSetProcessor<T> {
        T process(ResultSet rs) throws SQLException;
    }
} 