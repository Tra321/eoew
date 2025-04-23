import java.util.ArrayList;
import java.util.List;

/**
 * 仓库管理器类
 * 管理物品的增删改查等基本操作
 */
public class WarehouseManager {
    private List<WarehouseItem> items;
    
    public WarehouseManager() {
        this.items = new ArrayList<>();
        // 添加一些示例数据
        items.add(new WarehouseItem("I001", "手机", "电子设备", 3999.00, 100));
        items.add(new WarehouseItem("I002", "笔记本电脑", "电子设备", 6999.00, 50));
        items.add(new WarehouseItem("I003", "牛奶", "食品", 5.00, 1000));
    }
    
    /**
     * 添加物品到仓库
     */
    public void addItem(WarehouseItem item) {
        // 检查ID是否已存在
        for (WarehouseItem existingItem : items) {
            if (existingItem.getId().equals(item.getId())) {
                System.out.println("ID为 " + item.getId() + " 的物品已存在，无法添加！");
                return;
            }
        }
        items.add(item);
    }
    
    /**
     * 查找物品
     */
    public WarehouseItem findItem(String id) {
        for (WarehouseItem item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
    
    /**
     * 删除物品
     */
    public boolean removeItem(String id) {
        WarehouseItem itemToRemove = findItem(id);
        if (itemToRemove != null) {
            items.remove(itemToRemove);
            return true;
        }
        return false;
    }
    
    /**
     * 显示所有物品
     */
    public void showAllItems() {
        if (items.isEmpty()) {
            System.out.println("仓库中没有物品！");
            return;
        }
        
        System.out.println("\n=== 仓库物品列表 ===");
        System.out.printf("%-10s %-20s %-15s %-10s %-10s\n", 
                "ID", "名称", "类别", "价格", "数量");
        System.out.println("-------------------------------------------------------------------------");
        
        for (WarehouseItem item : items) {
            System.out.println(item);
        }
    }
    
    /**
     * 显示单个物品
     */
    public void displayItem(WarehouseItem item) {
        System.out.printf("%-10s %-20s %-15s %-10s %-10s\n", 
                "ID", "名称", "类别", "价格", "数量");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(item);
    }
    
    /**
     * 按名称搜索物品
     */
    public void searchItemsByName(String name) {
        List<WarehouseItem> results = new ArrayList<>();
        
        for (WarehouseItem item : items) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(item);
            }
        }
        
        if (results.isEmpty()) {
            System.out.println("未找到名称包含 '" + name + "' 的物品！");
        } else {
            System.out.println("\n=== 查询结果 ===");
            System.out.printf("%-10s %-20s %-15s %-10s %-10s\n", 
                    "ID", "名称", "类别", "价格", "数量");
            System.out.println("-------------------------------------------------------------------------");
            
            for (WarehouseItem item : results) {
                System.out.println(item);
            }
        }
    }
    
    /**
     * 按类别搜索物品
     */
    public void searchItemsByCategory(String category) {
        List<WarehouseItem> results = new ArrayList<>();
        
        for (WarehouseItem item : items) {
            if (item.getCategory().toLowerCase().contains(category.toLowerCase())) {
                results.add(item);
            }
        }
        
        if (results.isEmpty()) {
            System.out.println("未找到类别包含 '" + category + "' 的物品！");
        } else {
            System.out.println("\n=== 查询结果 ===");
            System.out.printf("%-10s %-20s %-15s %-10s %-10s\n", 
                    "ID", "名称", "类别", "价格", "数量");
            System.out.println("-------------------------------------------------------------------------");
            
            for (WarehouseItem item : results) {
                System.out.println(item);
            }
        }
    }
} 