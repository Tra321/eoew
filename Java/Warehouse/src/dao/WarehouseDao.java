package dao;

import model.Warehouse;

public interface WarehouseDao {
    void addWarehouse(Warehouse warehouse);
    void deleteWarehouse(String warehouseID);
    void updateWarehouse(Warehouse warehouse);
    Warehouse selectOneWarehouse(String warehouseID);
    void selectAllWarehouse();
}