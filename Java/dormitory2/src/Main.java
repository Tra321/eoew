import model.User;
import service.UserService;
import service.AccountService;
import util.PasswordUtil;

import java.util.Scanner;

public class Main {
    private static User currentUser = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("欢迎使用账号密码管理系统");
            
            while (true) {
                if (currentUser == null) {
                    showLoginMenu();
                } else {
                    showMainMenu();
                }
            }
        } catch (Exception e) {
            System.out.println("程序发生严重错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void showLoginMenu() {
        System.out.println("\n=== 登录菜单 ===");
        System.out.println("1. 登录");
        System.out.println("2. 注册");
        System.out.println("3. 退出系统");
        System.out.print("请选择操作：");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.out.println("感谢使用，再见！");
                System.exit(0);
            default:
                System.out.println("无效的选择，请重新输入");
        }
    }

    private static void showMainMenu() {
        System.out.println("\n=== 主菜单 ===");
        System.out.println("1. 查看所有账号");
        System.out.println("2. 添加新账号");
        System.out.println("3. 修改账号");
        System.out.println("4. 删除账号");
        System.out.println("5. 搜索账号");
        if (currentUser.getRole().equals("admin")) {
            System.out.println("6. 用户管理");
        }
        System.out.println("7. 修改密码");
        System.out.println("8. 退出登录");
        System.out.print("请选择操作：");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 清除缓冲区

        switch (choice) {
            case 1:
                AccountService.viewAllAccounts(currentUser.getId());
                break;
            case 2:
                AccountService.addAccount(currentUser.getId());
                break;
            case 3:
                AccountService.updateAccount(currentUser.getId());
                break;
            case 4:
                AccountService.deleteAccount(currentUser.getId());
                break;
            case 5:
                AccountService.searchAccounts(currentUser.getId());
                break;
            case 6:
                if (currentUser.getRole().equals("admin")) {
                    UserService.manageUsers();
                } else {
                    System.out.println("无权限访问此功能");
                }
                break;
            case 7:
                UserService.changePassword(currentUser.getId());
                break;
            case 8:
                currentUser = null;
                System.out.println("已退出登录");
                break;
            default:
                System.out.println("无效的选择，请重新输入");
        }
    }

    private static void login() {
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();

        if (username == null || username.trim().isEmpty()) {
            System.out.println("用户名不能为空！");
            return;
        }

        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        if (password == null || password.trim().isEmpty()) {
            System.out.println("密码不能为空！");
            return;
        }

        currentUser = UserService.login(username, password);
        if (currentUser != null) {
            System.out.println("登录成功！");
        } else {
            System.out.println("用户名或密码错误！");
        }
    }

    private static void register() {
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();

        // 检查用户名是否为空
        if (username == null || username.trim().isEmpty()) {
            System.out.println("用户名不能为空！");
            return;
        }

        System.out.print("请输入密码(密码是至少8位包含大小写字母、数字和特殊字符)：");
        String password = scanner.nextLine();

        if (!PasswordUtil.isPasswordStrong(password)) {
            System.out.println("密码强度不足！密码必须包含大小写字母、数字和特殊字符，且长度至少为8位");
            return;
        }

        if (UserService.register(username, password)) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败，用户名可能已存在");
        }
    }
}