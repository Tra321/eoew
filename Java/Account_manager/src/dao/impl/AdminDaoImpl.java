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
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="select * from admin where adminID=? and adminPassword=?";
            ps=con.prepareStatement(sql);
            ps.setString(1,admin.getAdminID());
            ps.setString(2, admin.getAdminPassword());
            rs=ps.executeQuery();
            admin  admins=null;
            if(rs.next()){
                admins=new admin();
                admins.setAdminID(rs.getString("adminID"));
                admins.setAdminPassword(rs.getString("adminPassword"));
                return admins;

            }
            else
                return null;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    //添加宿舍
    @Override
    public void addDormitory(Dormitory dormitory) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="insert into dormitory (dormitoryID,dormitoryBuild,dormitoryFloor) values(?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, dormitory.getDormitoryID());
            ps.setString(2, dormitory.getDormitoryBuild());
            ps.setInt(3, dormitory.getDormitoryFloor());
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
                String sql2="delete from live where dormitoryID=?";
                ps=con.prepareStatement(sql2);
                ps.setString(1, dormitoryID);
                int b=ps.executeUpdate();
                String sql3="delete from manage where dormitoryID=?";
                ps=con.prepareStatement(sql3);
                ps.setString(1, dormitoryID);
                int c=ps.executeUpdate();
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
    public void updateDormitory(Dormitory dormitory) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="update dormitory set dormitoryFloor=?,dormitoryBuild=? where dormitoryID=?";
            ps=con.prepareStatement(sql);
            ps.setString(3, dormitory.getDormitoryID());
            ps.setString(2, dormitory.getDormitoryBuild());
            ps.setInt(1, dormitory.getDormitoryFloor());
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
            Dormitory dormitory=null;
            if(rs.next()){
                dormitory = new Dormitory();
                dormitory.setDormitoryID(rs.getString("dormitoryID"));
                dormitory.setDormitoryBuild(rs.getString("dormitoryBuild"));
                dormitory.setDormitoryFloor(rs.getInt("dormitoryFloor"));
                System.out.println("dormitoryID "+"\t"+" dormitoryBuild"+"\t"+"dormitoryFloor"+"\t");
                System.out.println(dormitory.getDormitoryID()+" "+dormitory.getDormitoryBuild()+" "+dormitory.getDormitoryFloor());
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
            Dormitory dormitory=null;
            System.out.println("dormitoryID "+"\t"+" dormitoryBuild"+"\t"+"dormitoryFloor"+"\t");
            while(rs.next()){
                dormitory = new Dormitory();
                dormitory.setDormitoryID(rs.getString("dormitoryID"));
                dormitory.setDormitoryBuild(rs.getString("dormitoryBuild"));
                dormitory.setDormitoryFloor(rs.getInt("dormitoryFloor"));
                System.out.println(dormitory.getDormitoryID()+" \t "+dormitory.getDormitoryBuild()+" \t "+dormitory.getDormitoryFloor());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    //添加学生
    @Override
    public void addStudent(student student) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="insert into student (studentID,studntName,studentPassword,studentSex,studentBirthday,studentSubject,studentTel,studentEmail) values(?,?,?,?,?,?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, student.getStudentID());
            ps.setString(2,student.getStudentName());
            ps.setString(3, student.getStudentPassword());
            ps.setString(4,student.getStudentSex());
            ps.setString(5,student.getStudentBirthday());
            ps.setString(6,student.getStudentSubject());
            ps.setString(7,student.getStudentTel());
            ps.setString(8,student.getStudentEmail());

            int a = ps.executeUpdate();
            if(a>0){
                System.out.println("学生添加成功");
            }
            else{
                System.out.println("学生添加失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //删除学生
    @Override
    public void deleteStudent(String studentID) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql = "delete from student where studentID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1,studentID);
            int a=ps.executeUpdate();
            if(a>0){
                System.out.println("删除成功");
                String sql2="delete from live where studentID=?";
                ps=con.prepareStatement(sql2);
                ps.setString(1, studentID);
                int b=ps.executeUpdate();
            }
            else{
                System.out.println("输入用户名有误，删除失败");
            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //修改用户
    @Override
    public void updateStudent(student student) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="update student set studentPassword=? where studentID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, student.getStudentPassword());
            ps.setString(2, student.getStudentID());
            int a = ps.executeUpdate();
            if(a>0){
                System.out.println("学生修改成功");
            }
            else{
                System.out.println("输入有误，学生修改失败");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //查看某一学生
    @Override
    public void selectOneStudent(String studentID) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql ="select * from student where studentID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, studentID);
            rs=ps.executeQuery();
            student student=null;
            if(rs.next()){
                student = new student();
                student.setStudentID(rs.getString("studentName"));
                student.setStudentPassword(rs.getString("studentPassword"));
                System.out.println(student.getStudentID()+" "+student.getStudentPassword());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //查看全部学生
    @Override
    public void selectAllStudent() {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql ="select * from student ";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            student student=null;
            while(rs.next()){
                student = new student();
                student.setStudentID(rs.getString("studentID"));
                student.setStudentName(rs.getString("studentName"));
                student.setStudentPassword(rs.getString("studentPassword"));
                System.out.println(student.getStudentID()+" "+student.getStudentName()+" "+student.getStudentPassword());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //添加宿管
    @Override
    public void addManager(Manager manager) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="insert into manager (managerID,managerName,managerPassword,managerSex,managerBirthday,managerTitle,managerTel,managerEmail) values(?,?,?,?,?,?,?,?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, manager.getManagerID());
            ps.setString(2,manager.getManagerName());
            ps.setString(3, manager.getManagerPassword());
            ps.setString(4,manager.getManagerSex());
            ps.setString(5,manager.getManagerBirthday());
            ps.setString(6,manager.getManagerTitle());
            ps.setString(7,manager.getManagerTel());
            ps.setString(8,manager.getManagerEmail());

            int a = ps.executeUpdate();
            if(a>0){
                System.out.println("宿管添加成功");
            }
            else{
                System.out.println("宿管添加失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //修改宿管
    @Override
    public void updateManager(Manager manager) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql="update manager set managerPassword=? where managerID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, manager.getManagerPassword());
            ps.setString(2, manager.getManagerID());
            int a = ps.executeUpdate();
            if(a>0){
                System.out.println("宿管修改成功");
            }
            else{
                System.out.println("输入有误，宿管修改失败");
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //删除宿管
    @Override
    public void deleteManager(String managerID) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql = "delete from manager where managerID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, managerID);
            int a=ps.executeUpdate();
            if(a>0){
                System.out.println("删除成功");
                String sql2="delete from manage where managerID=?";
                ps=con.prepareStatement(sql2);
                ps.setString(1, managerID);
                int b=ps.executeUpdate();
            }
            else{
                System.out.println("输入用户名有误，删除失败");
            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //查看某一宿管
    @Override
    public void selectOneManager(String managerID) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql ="select * from manager where managerID=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, managerID);
            rs=ps.executeQuery();
            Manager manager=null;
            if(rs.next()){
                manager = new Manager();
                manager.setManagerID(rs.getString("managerName"));
                manager.setManagerPassword(rs.getString("managerPassword"));
                System.out.println(manager.getManagerID()+" "+manager.getManagerPassword());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //查看全部宿管
    @Override
    public void selectAllManager() {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement ps= null;
        ResultSet rs=null;
        try {
            con=BaseDao.getCon();
            String sql ="select * from manager ";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            Manager manager=null;
            while(rs.next()){
                manager = new Manager();
                manager.setManagerID(rs.getString("managerID"));
                manager.setManagerName(rs.getString("managerName"));
                manager.setManagerPassword(rs.getString("managerPassword"));
                System.out.println(manager.getManagerID()+" \t "+manager.getManagerName()+" \t "+manager.getManagerPassword()+" \t ");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
