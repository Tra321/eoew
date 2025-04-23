import java.sql.*;

public class test2 {
    public static void main(String[] args) {
        Connection con;
        //jdbc驱动
        String driver = "com.mysql.cj.jdbc.Driver";
        //这里是我的数据库的信息
        String url = "jdbc:mysql://localhost:3306/attendance_system?&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "Mysql135!";
        try {
            //注册JDBC驱动程序
            Class.forName(driver);
            //建立连接
            con = DriverManager.getConnection(url, user, password);
            //判断连接是否成功
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }

    public static void query(Connection con) {
        try {
            Statement statement = con.createStatement();
            String sql;
            //sql语句
            sql="select * from dormitory";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String dormitoryID = resultSet.getString("dormitoryID");
                String dormitoryBuild = resultSet.getString("dormitoryBuild");
                String dormitoryFloor = resultSet.getString("dormitoryFloor");
                System.out.println("dormitoryID:" + dormitoryID + "dormitoryBuild:" + dormitoryBuild + "dormitoryFloor:" + dormitoryFloor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }

    public static void insert(Connection con){
        try {
            // 编写SQL:
            String sql = "insert into dormitory (dormitoryID, dormitoryBuild, dormitoryFloor) values (?,?,?)";
            // 预处理SQL:
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            // 设置参数的值:
            preparedStatement.setString(1, "CW2101");
            preparedStatement.setString(2, "计算系统实训");
            preparedStatement.setInt(3, 1);
            // 执行SQL:
            int num = preparedStatement.executeUpdate();
            if(num > 0){
                System.out.println("保存成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }

    public static void update(Connection con){
        try {
            // 编写SQL:
            String sql = "update dormitory set dormitoryFloor = ? where dormitoryBuild = ?";
            // 预处理SQL:
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            // 设置参数的值:
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "计算系统实训");
            // 执行SQL:
            int num = preparedStatement.executeUpdate();
            if(num > 0){
                System.out.println("修改成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }

    public static void delete(Connection con){
        try {
            // 编写SQL:
            String sql = "delete from dormitory where dormitoryBuild = ?";
            // 预处理SQL:
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            // 设置参数的值:
            preparedStatement.setString(1, "计算系统实训");
            // 执行SQL:
            int num = preparedStatement.executeUpdate();
            if(num > 0){
                System.out.println("删除成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }
}

