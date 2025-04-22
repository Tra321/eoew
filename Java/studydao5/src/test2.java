import java.sql.*;

public class test2 {
    public static void main(String[] args) {
        Connection con;
        //jdbc驱动
        String driver = "com.mysql.cj.jdbc.Driver";
        //这里是我的数据库的信息
        String url = "jdbc:mysql://192.168.24.128:3306/choiceweb?&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "xiaor";
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
            sql="select * from course";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String courseID = resultSet.getString("courseID");
                String courseName = resultSet.getString("courseName");
                String courseCredit = resultSet.getString("courseCredit");
                System.out.println("courseID:" + courseID + "courseName:" + courseName + "courseCredit:" + courseCredit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }

    public static void insert(Connection con){
        try {
            // 编写SQL:
            String sql = "insert into course (courseID, courseName, courseCredit) values (?,?,?)";
            // 预处理SQL:
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            // 设置参数的值:
            preparedStatement.setString(1, "CW2101");
            preparedStatement.setString(2, "计算系统实训");
            preparedStatement.setFloat(3, Float.parseFloat("1.0"));
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
            String sql = "update course set courseCredit = ? where courseName = ?";
            // 预处理SQL:
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            // 设置参数的值:
            preparedStatement.setFloat(1, Float.parseFloat("3.0"));
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
            String sql = "delete from course where courseName = ?";
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

