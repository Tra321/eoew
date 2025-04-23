/**
 * 仓库物品类
 * 简化版本，只包含基本属性
 */
public class WarehouseItem {
    private String id;        // 物品ID
    private String name;      // 物品名称
    private String category;  // 物品类别
    private double price;     // 物品价格
    private int quantity;     // 物品数量
    
    public WarehouseItem(String id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return String.format("%-10s %-20s %-15s %-10.2f %-10d", 
                id, name, category, price, quantity);
    }
} 