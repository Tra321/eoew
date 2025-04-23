package service;

import java.util.Scanner;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import model.Class;
import model.admin;
import model.student;
import model.Attendance;
import until.AdminMenu;

public class AdminService {
    static Scanner sc = new Scanner(System.in);
    
    // 管理员登录
    public void adminLogin(String adminID, String adminPassword) {
        AdminDao dao = new AdminDaoImpl();
        admin admin = new admin();
        admin.setAdminID(adminID);
        admin.setAdminPassword(adminPassword);
        admin result = dao.login(admin);
        if (result != null) {
            System.out.println("登录成功");
            AdminMenu.ShowMenu();
        } else {
            System.out.println("用户名或密码错误");
        }
    }
    
    // ----- 班级管理 -----
    
    // 添加班级
    public static void addClass() {
        AdminDao dao = new AdminDaoImpl();
        Class clazz = new Class();
        System.out.println("请输入班级ID：");
        clazz.setClassID(sc.nextLine());
        System.out.println("请输入班级名称：");
        clazz.setClassName(sc.nextLine());
        dao.addClass(clazz);
        AdminMenu.ShowMenu();
    }
    
    // 删除班级
    public static void deleteClass() {
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要删除的班级ID：");
        String classID = sc.nextLine();
        dao.deleteClass(classID);
        AdminMenu.ShowMenu();
    }
    
    // 修改班级
    public static void updateClass() {
        AdminDao dao = new AdminDaoImpl();
        Class clazz = new Class();
        System.out.println("请输入要修改的班级ID：");
        clazz.setClassID(sc.nextLine());
        System.out.println("请输入新的班级名称：");
        clazz.setClassName(sc.nextLine());
        dao.updateClass(clazz);
        AdminMenu.ShowMenu();
    }
    
    // 查看单个班级
    public static void viewOneClass() {
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要查看的班级ID：");
        String classID = sc.nextLine();
        Class clazz = dao.selectOneClass(classID);
        if (clazz != null) {
            System.out.println("班级ID：" + clazz.getClassID());
            System.out.println("班级名称：" + clazz.getClassName());
        } else {
            System.out.println("未找到该班级");
        }
        AdminMenu.ShowMenu();
    }
    
    // 查看所有班级
    public static void viewAllClasses() {
        AdminDao dao = new AdminDaoImpl();
        System.out.println("班级列表：");
        dao.selectAllClass();
        AdminMenu.ShowMenu();
    }
    
    // ----- 学生管理 -----
    
    // 添加学生
    public static void addStudent() {
        AdminDao dao = new AdminDaoImpl();
        student student = new student();
        System.out.println("请输入学号：");
        student.setStudentID(sc.nextLine());
        System.out.println("请输入姓名：");
        student.setStudentName(sc.nextLine());
        System.out.println("请输入密码：");
        student.setStudentPassword(sc.nextLine());
        System.out.println("请输入班级ID：");
        student.setClassID(sc.nextLine());
        System.out.println("请输入电话：");
        student.setStudentTel(sc.nextLine());
        System.out.println("请输入邮箱：");
        student.setStudentEmail(sc.nextLine());
        dao.addStudent(student);
        AdminMenu.ShowMenu();
    }
    
    // 删除学生
    public static void deleteStudent() {
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要删除的学生学号：");
        String studentID = sc.nextLine();
        dao.deleteStudent(studentID);
        AdminMenu.ShowMenu();
    }
    
    // 修改学生
    public static void updateStudent() {
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要修改的学生学号：");
        String studentID = sc.nextLine();
        
        student existingStudent = dao.selectOneStudent(studentID);
        if (existingStudent == null) {
            System.out.println("未找到该学生");
            AdminMenu.ShowMenu();
            return;
        }
        
        System.out.println("请输入新姓名（不修改请直接回车）：");
        String name = sc.nextLine();
        if (!name.equals("")) {
            existingStudent.setStudentName(name);
        }
        
        System.out.println("请输入新密码（不修改请直接回车）：");
        String password = sc.nextLine();
        if (!password.equals("")) {
            existingStudent.setStudentPassword(password);
        }
        
        System.out.println("请输入新班级ID（不修改请直接回车）：");
        String classID = sc.nextLine();
        if (!classID.equals("")) {
            existingStudent.setClassID(classID);
        }
        
        System.out.println("请输入新电话（不修改请直接回车）：");
        String tel = sc.nextLine();
        if (!tel.equals("")) {
            existingStudent.setStudentTel(tel);
        }
        
        System.out.println("请输入新邮箱（不修改请直接回车）：");
        String email = sc.nextLine();
        if (!email.equals("")) {
            existingStudent.setStudentEmail(email);
        }
        
        dao.updateStudent(existingStudent);
        AdminMenu.ShowMenu();
    }
    
    // 查看单个学生
    public static void viewOneStudent() {
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要查看的学生学号：");
        String studentID = sc.nextLine();
        student student = dao.selectOneStudent(studentID);
        if (student != null) {
            System.out.println("学号：" + student.getStudentID());
            System.out.println("姓名：" + student.getStudentName());
            System.out.println("班级：" + student.getClassID());
            System.out.println("电话：" + student.getStudentTel());
            System.out.println("邮箱：" + student.getStudentEmail());
        } else {
            System.out.println("未找到该学生");
        }
        AdminMenu.ShowMenu();
    }
    
    // 查看所有学生
    public static void viewAllStudents() {
        AdminDao dao = new AdminDaoImpl();
        System.out.println("学生列表：");
        dao.selectAllStudent();
        AdminMenu.ShowMenu();
    }
    
    // ----- 考勤管理 -----
    
    // 添加考勤记录
    public static void addAttendance() {
        AdminDao dao = new AdminDaoImpl();
        Attendance attendance = new Attendance();
        
        System.out.println("请输入学生学号：");
        attendance.setStudentID(sc.nextLine());
        
        try {
            System.out.println("请输入考勤日期（格式：yyyy-MM-dd）：");
            String dateStr = sc.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(dateFormat.parse(dateStr).getTime());
            attendance.setAttendanceDate(date);
            
            System.out.println("请输入签到时间（格式：HH:mm:ss）：");
            String checkInStr = sc.nextLine();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Time checkInTime = new Time(timeFormat.parse(checkInStr).getTime());
            attendance.setCheckInTime(checkInTime);
            
            System.out.println("请输入签退时间（格式：HH:mm:ss）：");
            String checkOutStr = sc.nextLine();
            Time checkOutTime = new Time(timeFormat.parse(checkOutStr).getTime());
            attendance.setCheckOutTime(checkOutTime);
            
            System.out.println("请输入考勤状态（正常/迟到/缺勤/请假）：");
            attendance.setStatus(sc.nextLine());
            
            dao.addAttendance(attendance);
        } catch (Exception e) {
            System.out.println("输入格式错误：" + e.getMessage());
            e.printStackTrace();
        }
        
        AdminMenu.ShowMenu();
    }
    
    // 删除考勤记录
    public static void deleteAttendance() {
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要删除的考勤记录ID：");
        try {
            int id = Integer.parseInt(sc.nextLine());
            dao.deleteAttendance(id);
        } catch (NumberFormatException e) {
            System.out.println("ID必须是数字");
        }
        AdminMenu.ShowMenu();
    }
    
    // 修改考勤记录
    public static void updateAttendance() {
        AdminDao dao = new AdminDaoImpl();
        Attendance attendance = new Attendance();
        
        try {
            System.out.println("请输入要修改的考勤记录ID：");
            int id = Integer.parseInt(sc.nextLine());
            attendance.setId(id);
            
            System.out.println("请输入学生学号：");
            attendance.setStudentID(sc.nextLine());
            
            System.out.println("请输入考勤日期（格式：yyyy-MM-dd）：");
            String dateStr = sc.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(dateFormat.parse(dateStr).getTime());
            attendance.setAttendanceDate(date);
            
            System.out.println("请输入签到时间（格式：HH:mm:ss）：");
            String checkInStr = sc.nextLine();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Time checkInTime = new Time(timeFormat.parse(checkInStr).getTime());
            attendance.setCheckInTime(checkInTime);
            
            System.out.println("请输入签退时间（格式：HH:mm:ss）：");
            String checkOutStr = sc.nextLine();
            Time checkOutTime = new Time(timeFormat.parse(checkOutStr).getTime());
            attendance.setCheckOutTime(checkOutTime);
            
            System.out.println("请输入考勤状态（正常/迟到/缺勤/请假）：");
            attendance.setStatus(sc.nextLine());
            
            dao.updateAttendance(attendance);
        } catch (Exception e) {
            System.out.println("输入格式错误：" + e.getMessage());
            e.printStackTrace();
        }
        
        AdminMenu.ShowMenu();
    }
    
    // 查询某学生考勤记录
    public static void viewStudentAttendance() {
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要查询的学生学号：");
        String studentID = sc.nextLine();
        dao.selectStudentAttendance(studentID);
        AdminMenu.ShowMenu();
    }
    
    // 查询某日期考勤记录
    public static void viewDateAttendance() {
        AdminDao dao = new AdminDaoImpl();
        try {
            System.out.println("请输入要查询的日期（格式：yyyy-MM-dd）：");
            String dateStr = sc.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(dateFormat.parse(dateStr).getTime());
            
            dao.selectDateAttendance(date);
        } catch (Exception e) {
            System.out.println("日期格式错误：" + e.getMessage());
            e.printStackTrace();
        }
        
        AdminMenu.ShowMenu();
    }
    
    // 查询某班级某日期考勤记录
    public static void viewClassAttendance() {
        AdminDao dao = new AdminDaoImpl();
        try {
            System.out.println("请输入要查询的班级ID：");
            String classID = sc.nextLine();
            
            System.out.println("请输入要查询的日期（格式：yyyy-MM-dd）：");
            String dateStr = sc.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(dateFormat.parse(dateStr).getTime());
            
            dao.selectClassAttendance(classID, date);
        } catch (Exception e) {
            System.out.println("输入格式错误：" + e.getMessage());
            e.printStackTrace();
        }
        
        AdminMenu.ShowMenu();
    }
    
    // 生成考勤统计报表
    public static void generateAttendanceStatistics() {
        AdminDao dao = new AdminDaoImpl();
        try {
            System.out.println("请输入起始日期（格式：yyyy-MM-dd）：");
            String startDateStr = sc.nextLine();
            System.out.println("请输入结束日期（格式：yyyy-MM-dd）：");
            String endDateStr = sc.nextLine();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = new Date(dateFormat.parse(startDateStr).getTime());
            Date endDate = new Date(dateFormat.parse(endDateStr).getTime());
            
            dao.generateAttendanceStatistics(startDate, endDate);
        } catch (Exception e) {
            System.out.println("日期格式错误：" + e.getMessage());
            e.printStackTrace();
        }
        
        AdminMenu.ShowMenu();
    }
}