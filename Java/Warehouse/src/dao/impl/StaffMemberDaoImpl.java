package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.StaffMemberDao;
import model.StaffMember;

public class StaffMemberDaoImpl implements StaffMemberDao {
    // 这里应该有数据库连接的代码，暂时简化实现

    @Override
    public StaffMember login(StaffMember staffMember) {
        System.out.println("模拟登录操作");
        // 简化登录逻辑，任何有效ID和密码组合都可以登录
        if (staffMember.getStaffID() != null && !staffMember.getStaffID().isEmpty() && 
                staffMember.getStaffPassword() != null && !staffMember.getStaffPassword().isEmpty()) {
            return staffMember;
        }
        return null;
    }

    @Override
    public void addStaffMember(StaffMember staffMember) {
        System.out.println("模拟添加员工: " + staffMember.getStaffID());
        System.out.println("添加成功！");
    }

    @Override
    public void deleteStaffMember(String staffID) {
        System.out.println("模拟删除员工: " + staffID);
        System.out.println("删除成功！");
    }

    @Override
    public void updateStaffMember(StaffMember staffMember) {
        System.out.println("模拟更新员工: " + staffMember.getStaffID());
        System.out.println("更新成功！");
    }

    @Override
    public StaffMember selectOneStaffMember(String staffID) {
        System.out.println("模拟查询员工: " + staffID);
        System.out.println("员工ID: " + staffID);
        System.out.println("员工姓名: 张三");
        System.out.println("员工性别: 男");
        System.out.println("员工职位: 仓库管理员");
        System.out.println("联系电话: 13800138000");
        System.out.println("负责仓库: W001");
        
        // 返回一个模拟的员工对象
        StaffMember staff = new StaffMember();
        staff.setStaffID(staffID);
        staff.setStaffName("张三");
        staff.setStaffSex("男");
        staff.setStaffPosition("仓库管理员");
        staff.setStaffTel("13800138000");
        staff.setWarehouseID("W001");
        return staff;
    }

    @Override
    public void selectAllStaffMember() {
        System.out.println("模拟查询所有员工");
        System.out.println("员工ID\t员工姓名\t员工性别\t员工职位\t联系电话\t负责仓库");
        System.out.println("S001\t张三\t男\t仓库管理员\t13800138000\tW001");
        System.out.println("S002\t李四\t男\t仓库员工\t13900139000\tW001");
        System.out.println("S003\t王五\t女\t仓库主管\t13700137000\tW002");
    }
} 