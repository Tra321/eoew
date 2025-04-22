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

    // 管理员登录
    public void manLogin(String adminID, String adminPassword){
        AdminDao dao = new AdminDaoImpl();
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
}