package service;
import java.util.Scanner;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import model.student;
import until.StuMenu;

public class StudentService {
    static Scanner sc = new Scanner(System.in);
    public void studentLogin(String studentID,String studentPassword){
        StudentDao dao = new StudentDaoImpl();
        student student =new student();
        /*student.setStudentName(studentName);*/
        student.setStudentID(studentID);
        student.setStudentPassword(studentPassword);
        dao.login(student);
        if(dao.login(student)!=null){
            System.out.println("登录成功");
            StuMenu.showMenu(studentID);
        }
        else{
            System.out.println("用户名或密码错误");
        }

    }
    public static void studentChangePassWord(String studentID){
        StudentDao dao = new StudentDaoImpl();
        student student =new student();
        System.out.println("请输入新密码");
        String studentPassword = sc.nextLine();
        if(studentPassword==""||studentPassword.length()>10){
            System.exit(0);
        }
        else{
            student.setStudentID(studentID);
            student.setStudentPassword(studentPassword);
            dao.changePass(student);
            System.out.println("修改密码成功，新密码为："+studentPassword);
            StuMenu.showMenu(studentID);
        }

    }
    public static void studentStuDormitory(String studentID){
        StudentDao dao = new StudentDaoImpl();
        System.out.println("_________________________");
        System.out.println("已选宿舍信息为：");
        dao.getStuDormitory(studentID);
        StuMenu.showMenu(studentID);
    }
    public static void studentStuDormitoryFloor(String studentID) {
        StudentDao dao = new StudentDaoImpl();
        System.out.println("---------------------");
        System.out.println("获得总楼层为 ");
        dao.getStuDormitoryFloor(studentID);
        StuMenu.showMenu(studentID);
    }
    public static void studentDeleteDormitory(String studentID){
        StudentDao dao = new StudentDaoImpl();
        System.out.println("_________________________");
        System.out.println("想要删除的宿舍ID为：");
        String dormitoryID=sc.nextLine();
        dao.deleteDormitory(studentID,dormitoryID);
        StuMenu.showMenu(studentID);

    }
    public static void studentAvaDormitory(String studentID){
        StudentDao dao = new StudentDaoImpl();
        System.out.println("_________________________");
        System.out.println("可选宿舍为：");
        dao.getAvaDormitory(studentID);
        StuMenu.showMenu(studentID);
    }
    public static void studentChooserDormitory(String studentID){
        StudentDao dao = new StudentDaoImpl();
        System.out.println("_________________________");
        System.out.println("想要选择的宿舍ID为：");
        String dormitoryID=sc.nextLine();
        System.out.println("默认输入楼层为0");
        int dormitoryFloor = sc.nextInt();
        dao.chooseDormitory(studentID,dormitoryID,dormitoryFloor);
        StuMenu.showMenu(studentID);
    }
    public static void studentPrintStuDormitoryFloor(String studentID) {
        StudentDao dao = new StudentDaoImpl();
        System.out.println("---------------------");
        System.out.println("个人所在楼层为 ");
        dao.printStuDormitoryFloor(studentID);
        StuMenu.showMenu(studentID);
    }
}
