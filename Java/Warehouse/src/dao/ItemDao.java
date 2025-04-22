package dao;

import model.Item;

public interface ItemDao {
    void addItem(Item item);
    void deleteItem(String itemID);
    void updateItem(Item item);
    Item selectOneItem(String itemID);
    void selectAllItem();
    void updateItemQuantity(String itemID, int quantity);
    void searchItemByCategory(String category);
    void searchItemByWarehouse(String warehouseID);
}