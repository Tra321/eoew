package dao;

import model.Admin;
import java.util.List;

public interface AdminDao {
    /**
     * 管理员登录验证
     * @param admin 包含登录信息的管理员对象
     * @return 登录成功返回管理员对象，失败返回null
     */
    Admin login(Admin admin);
    
    /**
     * 修改管理员密码
     * @param adminId 管理员ID
     * @param newPassword 新密码
     * @return 修改成功返回true，失败返回false
     */
    boolean changePassword(String adminId, String newPassword);
    
    /**
     * 根据ID查找管理员
     * @param adminId 管理员ID
     * @return 管理员对象，未找到返回null
     */
    Admin findById(String adminId);
    
    /**
     * 添加新管理员
     * @param admin 管理员对象
     * @return 添加成功返回true，失败返回false
     */
    boolean add(Admin admin);
    
    /**
     * 更新管理员信息
     * @param admin 管理员对象
     * @return 更新成功返回true，失败返回false
     */
    boolean update(Admin admin);
    
    /**
     * 删除管理员
     * @param adminId 管理员ID
     * @return 删除成功返回true，失败返回false
     */
    boolean delete(String adminId);
    
    /**
     * 获取所有管理员列表
     * @return 管理员列表
     */
    List<Admin> findAll();
} 