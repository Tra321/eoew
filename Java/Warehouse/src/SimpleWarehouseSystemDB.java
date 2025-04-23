import java.util.Scanner;

/**
 * 简易仓库管理系统 - 数据库版
 * 使用MySQL数据库存储物品信息
 */
public class SimpleWarehouseSystemDB {
    public static void main(String[] args) {
        WarehouseManagerDB manager = new WarehouseManagerDB();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        System.out.println("=== 简易仓库管理系统(数据库版) ===");
        System.out.println("欢迎使用，请登录：");
        System.out.print("用户名: ");
        String username = scanner.nextLine();
        System.out.print("密码: ");
        String password = scanner.nextLine();
        
        if("admin".equals(username) && "123456".equals(password) || 
           "gx".equals(username) && "123".equals(password)) {
            System.out.println("登录成功！");
            
            while(running) {
                showMainMenu();
                System.out.print("请选择操作: ");
                String choice = scanner.nextLine();
                
                switch(choice) {
                    case "1": // 查看所有物品
                        manager.showAllItems();
                        break;
                    case "2": // 添加物品
                        addItem(scanner, manager);
                        break;
                    case "3": // 更新物品数量
                        updateItemQuantity(scanner, manager);
                        break;
                    case "4": // 删除物品
                        removeItem(scanner, manager);
                        break;
                    case "5": // 查询物品
                        searchItem(scanner, manager);
                        break;
                    case "0": // 退出
                        running = false;
                        System.out.println("谢谢使用，再见！");
                        break;
                    default:
                        System.out.println("无效选择，请重新输入！");
                }
                
                // 操作后暂停，等待用户按回车继续
                if(running) {
                    System.out.println("\n按回车键继续...");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("用户名或密码错误，程序退出！");
        }
        
        scanner.close();
    }
    
    private static void showMainMenu() {
        System.out.println("\n=== 简易仓库管理系统(数据库版) ===");
        System.out.println("1. 查看所有物品");
        System.out.println("2. 添加新物品");
        System.out.println("3. 更新物品数量");
        System.out.println("4. 删除物品");
        System.out.println("5. 查询物品");
        System.out.println("0. 退出系统");
    }
    
    private static void addItem(Scanner scanner, WarehouseManagerDB manager) {
        System.out.println("\n=== 添加新物品 ===");
        System.out.print("物品ID: ");
        String id = scanner.nextLine();
        System.out.print("物品名称: ");
        String name = scanner.nextLine();
        System.out.print("物品类别: ");
        String category = scanner.nextLine();
        
        double price = 0;
        int quantity = 0;
        
        try {
            System.out.print("物品价格: ");
            price = Double.parseDouble(scanner.nextLine());
            
            System.out.print("物品数量: ");
            quantity = Integer.parseInt(scanner.nextLine());
            
            WarehouseItem item = new WarehouseItem(id, name, category, price, quantity);
            manager.addItem(item);
        } catch (NumberFormatException e) {
            System.out.println("输入格式错误，请输入有效的数字！");
        }
    }
    
    private static void updateItemQuantity(Scanner scanner, WarehouseManagerDB manager) {
        System.out.println("\n=== 更新物品数量 ===");
        System.out.print("请输入物品ID: ");
        String id = scanner.nextLine();
        
        WarehouseItem item = manager.findItem(id);
        if (item != null) {
            System.out.println("当前物品: " + item.getName() + ", 当前数量: " + item.getQuantity());
            try {
                System.out.print("请输入新数量: ");
                int newQuantity = Integer.parseInt(scanner.nextLine());
                
                if (newQuantity >= 0) {
                    boolean success = manager.updateItemQuantity(id, newQuantity);
                    if (success) {
                        System.out.println("数量更新成功！");
                    } else {
                        System.out.println("数量更新失败！");
                    }
                } else {
                    System.out.println("数量不能为负数！");
                }
            } catch (NumberFormatException e) {
                System.out.println("输入格式错误，请输入有效的数字！");
            }
        } else {
            System.out.println("未找到ID为 " + id + " 的物品！");
        }
    }
    
    private static void removeItem(Scanner scanner, WarehouseManagerDB manager) {
        System.out.println("\n=== 删除物品 ===");
        System.out.print("请输入要删除物品的ID: ");
        String id = scanner.nextLine();
        
        boolean success = manager.removeItem(id);
        if (success) {
            System.out.println("物品删除成功！");
        } else {
            System.out.println("未找到ID为 " + id + " 的物品！");
        }
    }
    
    private static void searchItem(Scanner scanner, WarehouseManagerDB manager) {
        System.out.println("\n=== 查询物品 ===");
        System.out.println("1. 按ID查询");
        System.out.println("2. 按名称查询");
        System.out.println("3. 按类别查询");
        System.out.print("请选择查询方式: ");
        
        String choice = scanner.nextLine();
        
        switch(choice) {
            case "1":
                System.out.print("请输入物品ID: ");
                String id = scanner.nextLine();
                WarehouseItem item = manager.findItem(id);
                if (item != null) {
                    System.out.println("\n查询结果:");
                    manager.displayItem(item);
                } else {
                    System.out.println("未找到ID为 " + id + " 的物品！");
                }
                break;
            case "2":
                System.out.print("请输入物品名称: ");
                String name = scanner.nextLine();
                manager.searchItemsByName(name);
                break;
            case "3":
                System.out.print("请输入物品类别: ");
                String category = scanner.nextLine();
                manager.searchItemsByCategory(category);
                break;
            default:
                System.out.println("无效选择！");
        }
    }
} 