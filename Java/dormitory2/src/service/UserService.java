package service;

import model.User;
import util.PasswordUtil;
import dao.UserDao;
import dao.impl.UserDaoImpl;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private static UserDao userDao = new UserDaoImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static User login(String username, String password) {
        System.out.println("尝试登录用户: " + username);
        User user = userDao.findByUsername(username);
        if (user != null) {
            System.out.println("找到用户: " + username);
            boolean verified = PasswordUtil.verifyPassword(password, user.getPassword(), user.getSalt());
            System.out.println("密码验证结果: " + verified);
            if (verified) {
                // 更新最后登录时间
                userDao.updateLastLoginTime(user.getId());
                return user;
            }
        } else {
            System.out.println("未找到用户: " + username);
        }
        return null;
    }

    public static boolean register(String username, String password) {
        if (userDao.findByUsername(username) != null) {
            return false;
        }

        String salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);

        User user = new User(username, hashedPassword, "user");
        user.setSalt(salt);
        return userDao.save(user);
    }

    public static void changePassword(int userId) {
        System.out.print("请输入当前密码：");
        String currentPassword = scanner.nextLine();
        
        User user = userDao.findById(userId);
        if (user == null || !PasswordUtil.verifyPassword(currentPassword, user.getPassword(), user.getSalt())) {
            System.out.println("当前密码错误！");
            return;
        }

        System.out.print("请输入新密码：");
        String newPassword = scanner.nextLine();
        
        if (!PasswordUtil.isPasswordStrong(newPassword)) {
            System.out.println("密码强度不足！密码必须包含大小写字母、数字和特殊字符，且长度至少为8位");
            return;
        }

        String salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(newPassword, salt);
        
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        
        if (userDao.update(user)) {
            System.out.println("密码修改成功！");
        } else {
            System.out.println("密码修改失败！");
        }
    }

    public static void manageUsers() {
        while (true) {
            System.out.println("\n=== 用户管理 ===");
            System.out.println("1. 查看所有用户");
            System.out.println("2. 添加管理员");
            System.out.println("3. 删除用户");
            System.out.println("4. 返回主菜单");
            System.out.print("请选择操作：");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<User> users = userDao.findAll();
                    for (User user : users) {
                        System.out.println("ID: " + user.getId() + 
                                         ", 用户名: " + user.getUsername() + 
                                         ", 角色: " + user.getRole());
                    }
                    break;
                case 2:
                    System.out.print("请输入要设置为管理员的用户名：");
                    String username = scanner.nextLine();
                    User user = userDao.findByUsername(username);
                    if (user != null) {
                        user.setRole("admin");
                        if (userDao.update(user)) {
                            System.out.println("设置成功！");
                        } else {
                            System.out.println("设置失败！");
                        }
                    } else {
                        System.out.println("用户不存在！");
                    }
                    break;
                case 3:
                    System.out.print("请输入要删除的用户名：");
                    String deleteUsername = scanner.nextLine();
                    User deleteUser = userDao.findByUsername(deleteUsername);
                    if (deleteUser != null) {
                        if (userDao.delete(deleteUser.getId())) {
                            System.out.println("删除成功！");
                        } else {
                            System.out.println("删除失败！");
                        }
                    } else {
                        System.out.println("用户不存在！");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("无效的选择，请重新输入");
            }
        }
    }
} 