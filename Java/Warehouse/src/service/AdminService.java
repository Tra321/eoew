package service;

import java.util.Scanner;

import dao.WarehouseDao;
import dao.ItemDao;
import dao.StaffMemberDao;
import dao.impl.WarehouseDaoImpl;
import dao.impl.ItemDaoImpl;
import dao.impl.StaffMemberDaoImpl;
import model.Warehouse;
import model.Item;
import model.StaffMember;
import until.AdminMenu;

public class AdminService {
    static Scanner sc = new Scanner(System.in);
    
    // 管理员登录
    public void manLogin(String adminID, String adminPassword){
        // 暂时简化登录逻辑
        if("admin".equals(adminID) && "123456".equals(adminPassword)){
            System.out.println("登录成功");
            AdminMenu.ShowMenu();
        } else {
            System.out.println("用户名或密码错误");
        }
    }
    
    // 添加仓库
    public static void manAddWarehouse(){
        WarehouseDao dao = new WarehouseDaoImpl();
        Warehouse warehouse = new Warehouse();
        System.out.println("请输入要添加的仓库ID：");
        warehouse.setWarehouseID(sc.nextLine());
        System.out.println("请输入仓库位置：");
        warehouse.setWarehouseLocation(sc.next());
        System.out.println("请输入仓库容量：");
        warehouse.setWarehouseCapacity(sc.nextInt());
        System.out.println("请输入仓库类型：");
        warehouse.setWarehouseType(sc.next());
        dao.addWarehouse(warehouse);
        AdminMenu.ShowMenu();
    }
    
    // 删除仓库
    public static void manDeleteWarehouse(){
        WarehouseDao dao = new WarehouseDaoImpl();
        System.out.println("请输入要删除的仓库ID：");
        String warehouseID = sc.next();
        dao.deleteWarehouse(warehouseID);
        AdminMenu.ShowMenu();
    }
    
    // 修改仓库
    public static void manUpdateWarehouse(){
        WarehouseDao dao = new WarehouseDaoImpl();
        Warehouse warehouse = new Warehouse();
        System.out.println("请输入要修改的仓库ID：");
        warehouse.setWarehouseID(sc.nextLine());
        System.out.println("请输入修改后的仓库位置：");
        warehouse.setWarehouseLocation(sc.next());
        System.out.println("请输入修改后的仓库容量：");
        warehouse.setWarehouseCapacity(sc.nextInt());
        System.out.println("请输入修改后的仓库类型：");
        warehouse.setWarehouseType(sc.next());
        dao.updateWarehouse(warehouse);
        AdminMenu.ShowMenu();
    }
    
    // 查看某一仓库
    public static void manOneWarehouse(){
        WarehouseDao dao = new WarehouseDaoImpl();
        System.out.println("请输入要查看的仓库ID：");
        String warehouseID = sc.next();
        dao.selectOneWarehouse(warehouseID);
        AdminMenu.ShowMenu();
    }
    
    // 查看全部仓库
    public static void manAllWarehouse(){
        WarehouseDao dao = new WarehouseDaoImpl();
        System.out.println("已有仓库如下：");
        dao.selectAllWarehouse();
        AdminMenu.ShowMenu();
    }
    
    // 添加物品
    public static void manAddItem(){
        ItemDao dao = new ItemDaoImpl();
        Item item = new Item();
        System.out.println("请输入要添加的物品ID：");
        item.setItemID(sc.nextLine());
        System.out.println("请输入物品名称：");
        item.setItemName(sc.nextLine());
        System.out.println("请输入物品价格：");
        item.setItemPrice(sc.nextDouble());
        System.out.println("请输入物品数量：");
        item.setItemQuantity(sc.nextInt());
        sc.nextLine(); // 清除缓冲区
        System.out.println("请输入物品类别：");
        item.setItemCategory(sc.nextLine());
        System.out.println("请输入存放仓库ID：");
        item.setWarehouseID(sc.nextLine());
        System.out.println("请输入供应商信息：");
        item.setSupplierInfo(sc.nextLine());
        System.out.println("请输入过期日期：");
        item.setExpireDate(sc.nextLine());
        dao.addItem(item);
        AdminMenu.ShowMenu();
    }
    
    // 删除物品
    public static void manDeleteItem(){
        ItemDao dao = new ItemDaoImpl();
        System.out.println("请输入要删除的物品ID：");
        String itemID = sc.next();
        dao.deleteItem(itemID);
        AdminMenu.ShowMenu();
    }
    
    // 修改物品
    public static void manUpdateItem(){
        ItemDao dao = new ItemDaoImpl();
        Item item = new Item();
        System.out.println("请输入要修改的物品ID：");
        item.setItemID(sc.nextLine());
        System.out.println("请输入修改后的物品名称：");
        item.setItemName(sc.nextLine());
        System.out.println("请输入修改后的物品价格：");
        item.setItemPrice(sc.nextDouble());
        System.out.println("请输入修改后的物品数量：");
        item.setItemQuantity(sc.nextInt());
        sc.nextLine(); // 清除缓冲区
        System.out.println("请输入修改后的物品类别：");
        item.setItemCategory(sc.nextLine());
        System.out.println("请输入修改后的存放仓库ID：");
        item.setWarehouseID(sc.nextLine());
        dao.updateItem(item);
        AdminMenu.ShowMenu();
    }
    
    // 查看某一物品
    public static void manOneItem(){
        ItemDao dao = new ItemDaoImpl();
        System.out.println("请输入要查看的物品ID：");
        String itemID = sc.next();
        dao.selectOneItem(itemID);
        AdminMenu.ShowMenu();
    }
    
    // 查看全部物品
    public static void manAllItem(){
        ItemDao dao = new ItemDaoImpl();
        System.out.println("已有物品如下：");
        dao.selectAllItem();
        AdminMenu.ShowMenu();
    }
    
    // 添加员工
    public static void manAddStaff(){
        StaffMemberDao dao = new StaffMemberDaoImpl();
        StaffMember staff = new StaffMember();
        System.out.println("请输入要添加的员工ID：");
        staff.setStaffID(sc.nextLine());
        System.out.println("请输入员工姓名：");
        staff.setStaffName(sc.nextLine());
        System.out.println("请输入密码：");
        staff.setStaffPassword(sc.nextLine());
        System.out.println("请输入员工性别：");
        staff.setStaffSex(sc.nextLine());
        System.out.println("请输入员工生日：");
        staff.setStaffBirthday(sc.nextLine());
        System.out.println("请输入员工职位：");
        staff.setStaffPosition(sc.nextLine());
        System.out.println("请输入员工联系方式：");
        staff.setStaffTel(sc.nextLine());
        System.out.println("请输入员工Email：");
        staff.setStaffEmail(sc.nextLine());
        System.out.println("请输入员工负责的仓库ID：");
        staff.setWarehouseID(sc.nextLine());
        dao.addStaffMember(staff);
        AdminMenu.ShowMenu();
    }
    
    // 删除员工
    public static void manDeleteStaff(){
        StaffMemberDao dao = new StaffMemberDaoImpl();
        System.out.println("请输入要删除的员工ID：");
        String staffID = sc.next();
        dao.deleteStaffMember(staffID);
        AdminMenu.ShowMenu();
    }
    
    // 修改员工
    public static void manUpdateStaff(){
        StaffMemberDao dao = new StaffMemberDaoImpl();
        StaffMember staff = new StaffMember();
        System.out.println("请输入要修改的员工ID：");
        staff.setStaffID(sc.nextLine());
        System.out.println("请输入修改后的密码：");
        staff.setStaffPassword(sc.nextLine());
        dao.updateStaffMember(staff);
        AdminMenu.ShowMenu();
    }
    
    // 查看某一员工
    public static void manOneStaff(){
        StaffMemberDao dao = new StaffMemberDaoImpl();
        System.out.println("请输入要查看的员工ID：");
        String staffID = sc.next();
        dao.selectOneStaffMember(staffID);
        AdminMenu.ShowMenu();
    }
    
    // 查看全部员工
    public static void manAllStaff(){
        StaffMemberDao dao = new StaffMemberDaoImpl();
        System.out.println("已有员工信息如下：");
        dao.selectAllStaffMember();
        AdminMenu.ShowMenu();
    }
}