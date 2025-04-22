package service;

import java.util.Scanner;

import dao.ItemDao;
import dao.impl.ItemDaoImpl;
import model.Item;

public class ItemService {
    static Scanner sc = new Scanner(System.in);
    
    // 仅提供一些共用的物品服务方法，主要用于扩展
    
    // 按类别查询物品
    public static void searchItemByCategory(String category){
        ItemDao dao = new ItemDaoImpl();
        dao.searchItemByCategory(category);
    }
    
    // 按仓库查询物品
    public static void searchItemByWarehouse(String warehouseID){
        ItemDao dao = new ItemDaoImpl();
        dao.searchItemByWarehouse(warehouseID);
    }
    
    // 查看物品详情
    public static Item getItemDetail(String itemID){
        ItemDao dao = new ItemDaoImpl();
        return dao.selectOneItem(itemID);
    }
    
    // 更新物品数量
    public static void updateItemQuantity(String itemID, int quantity){
        ItemDao dao = new ItemDaoImpl();
        dao.updateItemQuantity(itemID, quantity);
    }
} 