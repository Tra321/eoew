-- 创建仓库管理系统数据库
CREATE DATABASE IF NOT EXISTS warehouse_management;

-- 使用该数据库
USE warehouse_management;

-- 创建物品表
CREATE TABLE IF NOT EXISTS items (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INT NOT NULL
);

-- 插入示例数据
INSERT INTO items (id, name, category, price, quantity) VALUES 
('I001', '手机', '电子设备', 3999.00, 100),
('I002', '笔记本电脑', '电子设备', 6999.00, 50),
('I003', '牛奶', '食品', 5.00, 1000); 