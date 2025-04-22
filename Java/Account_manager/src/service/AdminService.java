package service;

import java.util.Scanner;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import model.Dormitory;
import model.admin;
import model.student;
import model.Manager;
import until.AdminMenu;

public class AdminService {
    static Scanner sc = new Scanner(System.in);
    //管理员登录
    public void manLogin(String adminID,String adminPassword){
        AdminDao dao= new AdminDaoImpl();
        admin admin = new admin();
        admin.setAdminID(adminID);
        admin.setAdminPassword(adminPassword);
        dao.login(admin);
        if(dao.login(admin)!=null){
            System.out.println("登录成功");
            AdminMenu.ShowMenu();

        }
        else{
            System.out.println("用户名或密码错误");
        }
    }
    //添加宿舍
    public static void manAddDormitory(){
        AdminDao dao = new AdminDaoImpl();
        Dormitory dormitory=new Dormitory();
        System.out.println("请输入要填加的宿舍ID：");
        dormitory.setDormitoryID(sc.nextLine());
        System.out.println("请输入要填加的宿舍楼：");
        dormitory.setDormitoryBuild(sc.next());
        System.out.println("请输入要添加的宿舍楼层：");
        dormitory.setDormitoryFloor(sc.nextInt());
        dao.addDormitory(dormitory);
        AdminMenu.ShowMenu();
    }
    //删除宿舍
    public static void manDeleteDormitory(){
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要删除的宿舍ID：");
        String dormitoryID=sc.next();
        dao.deleteDormitory(dormitoryID);
        AdminMenu.ShowMenu();
    }
    //修改宿舍
    public static void manUpdateDormitory(){
        AdminDao dao = new AdminDaoImpl();
        Dormitory dormitory=new Dormitory();
        System.out.println("请输入要修改的宿舍ID：");
        dormitory.setDormitoryID(sc.nextLine());
        System.out.println("请输入修改后的宿舍楼：");
        dormitory.setDormitoryBuild(sc.nextLine());
        System.out.println("请输入要修改的宿舍层：");
        dormitory.setDormitoryFloor(sc.nextInt());
        dao.updateDormitory(dormitory);
        AdminMenu.ShowMenu();
    }
    //查看某一宿舍
    public static void manOneDormitory(){
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要查看的宿舍ID：");
        String dormitoryID=sc.next();
        dao.selectOneDormitory(dormitoryID);
        AdminMenu.ShowMenu();
    }
    //查看全部宿舍
    public static void manAllDormitory(){
        AdminDao dao = new AdminDaoImpl();
        System.out.println("已有宿舍如下：");
        dao.selectAllDormitory();
        AdminMenu.ShowMenu();
    }
    //添加学生
    public static void manAddStudent(){
        AdminDao dao = new AdminDaoImpl();
        student student = new student();
        System.out.println("请输入要填加的学生学号：");
        student.setStudentID(sc.nextLine());
        System.out.println("请输入学生姓名");
        student.setStudentName(sc.nextLine());
        System.out.println("请输入密码：");
        student.setStudentPassword(sc.nextLine());
        System.out.println("请输入学生性别：");
        student.setStudentSex(sc.nextLine());
        System.out.println("请输入学生生日：");
        student.setStudentBirthday(sc.nextLine());
        System.out.println("请输入学生专业");
        student.setStudentSubject(sc.nextLine());
        System.out.println("请输入学生联系方式");
        student.setStudentTel(sc.nextLine());
        System.out.println("请输入学生Email");
        student.setStudentEmail(sc.nextLine());
        dao.addStudent(student);
        AdminMenu.ShowMenu();
    }
    //添加宿管
    public static void manAddManager(){
        AdminDao dao = new AdminDaoImpl();
        Manager manager = new Manager();
        System.out.println("请输入要填加的宿管学号：");
        manager.setManagerID(sc.nextLine());
        System.out.println("请输入宿管姓名");
        manager.setManagerName(sc.nextLine());
        System.out.println("请输入密码：");
        manager.setManagerPassword(sc.nextLine());
        System.out.println("请输入宿管性别：");
        manager.setManagerSex(sc.nextLine());
        System.out.println("请输入宿管生日：");
        manager.setManagerBirthday(sc.nextLine());
        System.out.println("请输入宿管职称");
        manager.setManagerTitle(sc.nextLine());
        System.out.println("请输入宿管联系方式");
        manager.setManagerTel(sc.nextLine());
        System.out.println("请输入宿管Email");
        manager.setManagerEmail(sc.nextLine());
        dao.addManager(manager);
        AdminMenu.ShowMenu();
    }

    //删除宿管
    public static void manDeleteManager(){
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要删除的宿管工号：");
        String managerID = sc.next();
        dao.deleteManager(managerID);
        AdminMenu.ShowMenu();
    }
    //修改宿管
    public static void manUpdateManager(){
        AdminDao dao = new AdminDaoImpl();
        Manager manager= new Manager();
        System.out.println("请输入要修改的宿管工号：");
        manager.setManagerID(sc.next());
        System.out.println("请输入修改后的密码：");
        manager.setManagerPassword(sc.nextLine());
        dao.updateManager(manager);
        AdminMenu.ShowMenu();
    }

    //删除学生
    public static void manDeleteUser(){
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要删除的学生学号：");
        String studentID = sc.next();
        dao.deleteStudent(studentID);
        AdminMenu.ShowMenu();
    }
    //修改学生
    public static void manUpdateUser(){
        AdminDao dao = new AdminDaoImpl();
        student student= new student();
        System.out.println("请输入要修改的学生学号：");
        student.setStudentID(sc.next());
        System.out.println("请输入修改后的密码：");
        student.setStudentPassword(sc.nextLine());
        dao.updateStudent(student);
        AdminMenu.ShowMenu();
    }
    //查看某一学生
    public static void manOneUser(){
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要查看的学生学号：");
        String studentID = sc.next();
        dao.selectOneStudent(studentID);
        AdminMenu.ShowMenu();
    }
    //查看全部学生
    public static void manAllUser(){
        AdminDao dao = new AdminDaoImpl();
        System.out.println("已有学生信息如下：");
        System.out.println("学生ID \t 学生姓名 \t 学生密码 \t ");
        dao.selectAllStudent();
        AdminMenu.ShowMenu();
    }
    //查看某一宿管
    public static void manOneManager(){
        AdminDao dao = new AdminDaoImpl();
        System.out.println("请输入要查看的宿管学号：");
        String managerID = sc.next();
        dao.selectOneManager(managerID);
        AdminMenu.ShowMenu();
    }
    //查看全部宿管
    public static void manAllManager(){
        AdminDao dao = new AdminDaoImpl();
        System.out.println("已有宿管信息如下：");
        System.out.println("宿管ID \t 宿管姓名 \t 宿管密码 \t ");
        dao.selectAllManager();
        AdminMenu.ShowMenu();
    }

}