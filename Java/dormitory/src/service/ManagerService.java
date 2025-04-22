package service;
import java.util.Scanner;

import dao.ManagerDao;
import dao.impl.ManagerDaoImpl;
import model.Manager;
import until.ManagerMenu;

public class ManagerService {
    static Scanner sc = new Scanner(System.in);
    public void managerLogin(String managerID,String managerPassword){
        ManagerDao dao = new ManagerDaoImpl();
        Manager manager =new Manager();
        /*manager.setmanagerName(managerName);*/
        manager.setManagerID(managerID);
        manager.setManagerPassword(managerPassword);
        dao.login(manager);
        if(dao.login(manager)!=null){
            System.out.println("登录成功");
            ManagerMenu.ShowMenu(managerID);
        }
        else{
            System.out.println("用户名或密码错误");
        }

    }
    public static void managerChangePassWord(String managerID){
        ManagerDao dao = new ManagerDaoImpl();
        Manager manager =new Manager();
        System.out.println("请输入新密码");
        String managerPassword = sc.nextLine();
        if(managerPassword==""||managerPassword.length()>10){
            System.exit(0);
        }
        else{
            manager.setManagerID(managerID);
            manager.setManagerPassword(managerPassword);
            dao.changePass(manager);
            System.out.println("修改密码成功，新密码为："+managerPassword);
            ManagerMenu.ShowMenu(managerID);
        }

    }

    /**
     * 录入失败
     * @param managerID,dormitoryFloor
     */
    public static void managerStuFloor(String managerID){
        ManagerDao dao = new ManagerDaoImpl();
        System.out.println("_________________________");
        System.out.println("请输入学生ID:");
        String studentID = sc.nextLine();
        System.out.println("请输入宿舍ID:");
        String dormitoryID = sc.nextLine();
        System.out.println("请输入宿舍楼层:");
        float dormitoryFloor = sc.nextFloat();
        dao.addStudentLive(managerID, studentID, dormitoryID, dormitoryFloor);
        ManagerMenu.ShowMenu(managerID);
    }

    public static void managerAvaFloor(String managerID){
        ManagerDao dao = new ManagerDaoImpl();
        System.out.println("_________________________");
        System.out.println("您管理的宿舍信息为：");
        dao.getAllDormitoryFloor(managerID);
        ManagerMenu.ShowMenu(managerID);
    }

    public static void managerInspection(String managerID){
        ManagerDao dao = new ManagerDaoImpl();
        System.out.println("_________________________");
        System.out.println("请输入宿舍ID:");
        String dormitoryID = sc.nextLine();
        System.out.println("请输入巡查日期(格式:yyyy-MM-dd):");
        String inspectionDate = sc.nextLine();
        System.out.println("请输入巡查备注:");
        String remark = sc.nextLine();
        dao.addInspection(managerID, dormitoryID, inspectionDate, remark);
        ManagerMenu.ShowMenu(managerID);
    }

}

