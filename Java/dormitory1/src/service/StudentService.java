package service;
import java.util.Scanner;
import java.sql.Date;
import java.text.SimpleDateFormat;

import dao.StudentDao;
import dao.AttendanceDao;
import dao.impl.StudentDaoImpl;
import dao.impl.AttendanceDaoImpl;
import model.student;
import until.StuMenu;

public class StudentService {
    static Scanner sc = new Scanner(System.in);
    
    // 学生登录
    public void studentLogin(String studentID, String studentPassword) {
        StudentDao dao = new StudentDaoImpl();
        student student = new student();
        student.setStudentID(studentID);
        student.setStudentPassword(studentPassword);
        student result = dao.login(student);
        if (result != null) {
            System.out.println("登录成功");
            StuMenu.showMenu(studentID);
        } else {
            System.out.println("用户名或密码错误");
        }
    }
    
    // 修改密码
    public static void studentChangePassword(String studentID) {
        StudentDao dao = new StudentDaoImpl();
        student student = new student();
        System.out.println("请输入新密码");
        String studentPassword = sc.nextLine();
        if (studentPassword.equals("") || studentPassword.length() > 10) {
            System.out.println("密码不能为空且长度不能超过10个字符");
            return;
        } else {
            student.setStudentID(studentID);
            student.setStudentPassword(studentPassword);
            dao.changePass(student);
            System.out.println("修改密码成功，新密码为：" + studentPassword);
            StuMenu.showMenu(studentID);
        }
    }
    
    // 更新个人信息
    public static void updateStudentProfile(String studentID) {
        StudentDao dao = new StudentDaoImpl();
        student currentStudent = dao.getStudentInfo(studentID);
        
        if (currentStudent == null) {
            System.out.println("获取学生信息失败");
            return;
        }
        
        System.out.println("====== 更新个人信息 ======");
        System.out.println("当前信息：");
        System.out.println("姓名：" + currentStudent.getStudentName());
        System.out.println("班级：" + currentStudent.getClassID());
        System.out.println("电话：" + currentStudent.getStudentTel());
        System.out.println("邮箱：" + currentStudent.getStudentEmail());
        
        System.out.println("\n请输入新姓名（不修改请直接回车）：");
        String name = sc.nextLine();
        if (!name.equals("")) {
            currentStudent.setStudentName(name);
        }
        
        System.out.println("请输入新电话（不修改请直接回车）：");
        String tel = sc.nextLine();
        if (!tel.equals("")) {
            currentStudent.setStudentTel(tel);
        }
        
        System.out.println("请输入新邮箱（不修改请直接回车）：");
        String email = sc.nextLine();
        if (!email.equals("")) {
            currentStudent.setStudentEmail(email);
        }
        
        dao.updateProfile(currentStudent);
        System.out.println("个人信息更新成功！");
        StuMenu.showMenu(studentID);
    }
    
    // 查看个人信息
    public static void viewStudentInfo(String studentID) {
        StudentDao dao = new StudentDaoImpl();
        student student = dao.getStudentInfo(studentID);
        
        if (student != null) {
            System.out.println("====== 个人信息 ======");
            System.out.println("学号：" + student.getStudentID());
            System.out.println("姓名：" + student.getStudentName());
            System.out.println("班级：" + student.getClassID());
            System.out.println("电话：" + student.getStudentTel());
            System.out.println("邮箱：" + student.getStudentEmail());
        } else {
            System.out.println("获取学生信息失败");
        }
        
        StuMenu.showMenu(studentID);
    }
    
    // 查看个人考勤记录
    public static void viewAttendanceRecords(String studentID) {
        AttendanceDao dao = new AttendanceDaoImpl();
        System.out.println("====== 个人考勤记录 ======");
        dao.selectStudentAttendance(studentID);
        StuMenu.showMenu(studentID);
    }
    
    // 查看考勤统计
    public static void viewAttendanceStatistics(String studentID) {
        AttendanceDao dao = new AttendanceDaoImpl();
        System.out.println("====== 考勤统计 ======");
        System.out.println("请输入起始日期（格式：yyyy-MM-dd）：");
        String startDateStr = sc.nextLine();
        System.out.println("请输入结束日期（格式：yyyy-MM-dd）：");
        String endDateStr = sc.nextLine();
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = new Date(sdf.parse(startDateStr).getTime());
            Date endDate = new Date(sdf.parse(endDateStr).getTime());
            
            dao.generateAttendanceReport(studentID, startDate, endDate);
        } catch (Exception e) {
            System.out.println("日期格式错误，请使用 yyyy-MM-dd 格式");
            e.printStackTrace();
        }
        
        StuMenu.showMenu(studentID);
    }
    
    // 查看班级同学
    public static void viewClassmates(String studentID) {
        StudentDao dao = new StudentDaoImpl();
        student student = dao.getStudentInfo(studentID);
        
        if (student != null) {
            String classID = student.getClassID();
            System.out.println("====== 班级同学 (" + classID + ") ======");
            dao.getStudentsByClass(classID);
        } else {
            System.out.println("获取学生信息失败");
        }
        
        StuMenu.showMenu(studentID);
    }
}
