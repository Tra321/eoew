package service;

import java.util.Scanner;

import dao.ItemDao;
import dao.StaffMemberDao;
import dao.impl.ItemDaoImpl;
import dao.impl.StaffMemberDaoImpl;
import model.Item;
import model.StaffMember;
import until.StaffMenu;

public class StaffMemberService {
    static Scanner sc = new Scanner(System.in);
    
    // 员工登录
    public void staffLogin(String staffID, String staffPassword){
        StaffMemberDao dao = new StaffMemberDaoImpl();
        StaffMember staff = new StaffMember();
        staff.setStaffID(staffID);
        staff.setStaffPassword(staffPassword);
        StaffMember loginStaff = dao.login(staff);
        if(loginStaff != null){
            System.out.println("登录成功");
            StaffMenu.ShowMenu();
        } else {
            System.out.println("用户名或密码错误");
        }
    }
    
    // 查看某一物品
    public static void staffOneItem(){
        ItemDao dao = new ItemDaoImpl();
        System.out.println("请输入要查看的物品ID：");
        String itemID = sc.next();
        dao.selectOneItem(itemID);
        StaffMenu.ShowMenu();
    }
    
    // 查看全部物品
    public static void staffAllItem(){
        ItemDao dao = new ItemDaoImpl();
        System.out.println("已有物品如下：");
        dao.selectAllItem();
        StaffMenu.ShowMenu();
    }
    
    // 按类别查询物品
    public static void staffSearchItemByCategory(){
        ItemDao dao = new ItemDaoImpl();
        System.out.println("请输入要查询的物品类别：");
        String category = sc.next();
        dao.searchItemByCategory(category);
        StaffMenu.ShowMenu();
    }
    
    // 按仓库查询物品
    public static void staffSearchItemByWarehouse(){
        ItemDao dao = new ItemDaoImpl();
        System.out.println("请输入要查询的仓库ID：");
        String warehouseID = sc.next();
        dao.searchItemByWarehouse(warehouseID);
        StaffMenu.ShowMenu();
    }
    
    // 更新物品数量
    public static void staffUpdateItemQuantity(){
        ItemDao dao = new ItemDaoImpl();
        System.out.println("请输入要更新的物品ID：");
        String itemID = sc.next();
        System.out.println("请输入新的物品数量：");
        int quantity = sc.nextInt();
        dao.updateItemQuantity(itemID, quantity);
        StaffMenu.ShowMenu();
    }
    
    // 修改个人信息
    public static void staffUpdateInfo(){
        StaffMemberDao dao = new StaffMemberDaoImpl();
        StaffMember staff = new StaffMember();
        System.out.println("请输入您的员工ID：");
        staff.setStaffID(sc.next());
        System.out.println("请输入修改后的密码：");
        staff.setStaffPassword(sc.next());
        dao.updateStaffMember(staff);
        StaffMenu.ShowMenu();
    }
} 