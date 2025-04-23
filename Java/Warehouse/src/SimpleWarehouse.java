import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 极简仓库管理系统
 * 所有功能都集成在一个文件中
 */
public class SimpleWarehouse {
    
    // 用于存储物品的列表
    private static List<Item> inventory = new ArrayList<>();
    
    public static void main(String[] args) {
        // 添加一些示例数据
        inventory.add(new Item("1001", "手机", 100, 3999.99));
        inventory.add(new Item("1002", "笔记本电脑", 50, 6999.99));
        inventory.add(new Item("1003", "耳机", 200, 299.99));
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        // 登录
        System.out.println("=== 极简仓库管理系统 ===");
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();
        
        // 简单验证，固定用户名密码
        if (!"admin".equals(username) || !"123456".equals(password)) {
            System.out.println("用户名或密码错误!");
            scanner.close();
            return;
        }
        
        System.out.println("登录成功!");
        
        // 主循环
        while (running) {
            displayMenu();
            System.out.print("请选择操作: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    displayAllItems();
                    break;
                case "2":
                    addNewItem(scanner);
                    break;
                case "3":
                    updateItemQuantity(scanner);
                    break;
                case "4":
                    removeItem(scanner);
                    break;
                case "5":
                    searchItem(scanner);
                    break;
                case "0":
                    running = false;
                    System.out.println("感谢使用，再见!");
                    break;
                default:
                    System.out.println("无效选择，请重试!");
            }
            
            if (running) {
                System.out.println("\n按回车键继续...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    // 显示主菜单
    private static void displayMenu() {
        System.out.println("\n=== 极简仓库管理系统 ===");
        System.out.println("1. 查看所有物品");
        System.out.println("2. 添加新物品");
        System.out.println("3. 更新物品数量");
        System.out.println("4. 删除物品");
        System.out.println("5. 搜索物品");
        System.out.println("0. 退出系统");
    }
    
    // 显示所有物品
    private static void displayAllItems() {
        if (inventory.isEmpty()) {
            System.out.println("仓库中没有物品!");
            return;
        }
        
        System.out.println("\n=== 仓库物品列表 ===");
        System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "名称", "数量", "价格");
        System.out.println("----------------------------------------------------");
        
        for (Item item : inventory) {
            System.out.printf("%-10s %-20s %-10d %-10.2f\n", 
                    item.getId(), item.getName(), item.getQuantity(), item.getPrice());
        }
    }
    
    // 添加新物品
    private static void addNewItem(Scanner scanner) {
        System.out.println("\n=== 添加新物品 ===");
        
        System.out.print("请输入物品ID: ");
        String id = scanner.nextLine();
        
        // 检查ID是否已存在
        for (Item item : inventory) {
            if (item.getId().equals(id)) {
                System.out.println("此ID已存在，请使用其他ID!");
                return;
            }
        }
        
        System.out.print("请输入物品名称: ");
        String name = scanner.nextLine();
        
        int quantity = 0;
        double price = 0.0;
        
        try {
            System.out.print("请输入物品数量: ");
            quantity = Integer.parseInt(scanner.nextLine());
            
            System.out.print("请输入物品价格: ");
            price = Double.parseDouble(scanner.nextLine());
            
            if (quantity < 0 || price < 0) {
                System.out.println("数量和价格不能为负数!");
                return;
            }
            
            // 创建并添加新物品
            Item newItem = new Item(id, name, quantity, price);
            inventory.add(newItem);
            System.out.println("物品添加成功!");
            
        } catch (NumberFormatException e) {
            System.out.println("输入格式错误，请输入有效的数字!");
        }
    }
    
    // 更新物品数量
    private static void updateItemQuantity(Scanner scanner) {
        System.out.println("\n=== 更新物品数量 ===");
        System.out.print("请输入物品ID: ");
        String id = scanner.nextLine();
        
        Item item = findItemById(id);
        if (item == null) {
            System.out.println("找不到ID为 " + id + " 的物品!");
            return;
        }
        
        System.out.println("当前物品: " + item.getName() + ", 当前数量: " + item.getQuantity());
        
        try {
            System.out.print("请输入新数量: ");
            int newQuantity = Integer.parseInt(scanner.nextLine());
            
            if (newQuantity < 0) {
                System.out.println("数量不能为负数!");
                return;
            }
            
            item.setQuantity(newQuantity);
            System.out.println("数量更新成功!");
            
        } catch (NumberFormatException e) {
            System.out.println("输入格式错误，请输入有效的数字!");
        }
    }
    
    // 删除物品
    private static void removeItem(Scanner scanner) {
        System.out.println("\n=== 删除物品 ===");
        System.out.print("请输入要删除的物品ID: ");
        String id = scanner.nextLine();
        
        Item item = findItemById(id);
        if (item == null) {
            System.out.println("找不到ID为 " + id + " 的物品!");
            return;
        }
        
        inventory.remove(item);
        System.out.println("物品 '" + item.getName() + "' 已成功删除!");
    }
    
    // 搜索物品
    private static void searchItem(Scanner scanner) {
        System.out.println("\n=== 搜索物品 ===");
        System.out.println("1. 按ID搜索");
        System.out.println("2. 按名称搜索");
        System.out.print("请选择搜索方式: ");
        
        String choice = scanner.nextLine();
        
        if ("1".equals(choice)) {
            System.out.print("请输入物品ID: ");
            String id = scanner.nextLine();
            
            Item item = findItemById(id);
            if (item == null) {
                System.out.println("找不到ID为 " + id + " 的物品!");
            } else {
                System.out.println("\n=== 搜索结果 ===");
                System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "名称", "数量", "价格");
                System.out.println("----------------------------------------------------");
                System.out.printf("%-10s %-20s %-10d %-10.2f\n", 
                        item.getId(), item.getName(), item.getQuantity(), item.getPrice());
            }
        } else if ("2".equals(choice)) {
            System.out.print("请输入物品名称(部分名称即可): ");
            String name = scanner.nextLine().toLowerCase();
            
            List<Item> results = new ArrayList<>();
            for (Item item : inventory) {
                if (item.getName().toLowerCase().contains(name)) {
                    results.add(item);
                }
            }
            
            if (results.isEmpty()) {
                System.out.println("找不到名称包含 '" + name + "' 的物品!");
            } else {
                System.out.println("\n=== 搜索结果 ===");
                System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "名称", "数量", "价格");
                System.out.println("----------------------------------------------------");
                
                for (Item item : results) {
                    System.out.printf("%-10s %-20s %-10d %-10.2f\n", 
                            item.getId(), item.getName(), item.getQuantity(), item.getPrice());
                }
            }
        } else {
            System.out.println("无效选择!");
        }
    }
    
    // 通过ID查找物品
    private static Item findItemById(String id) {
        for (Item item : inventory) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
    
    // 物品类
    static class Item {
        private String id;
        private String name;
        private int quantity;
        private double price;
        
        public Item(String id, String name, int quantity, double price) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }
        
        public String getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
        
        public int getQuantity() {
            return quantity;
        }
        
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        
        public double getPrice() {
            return price;
        }
    }
} 