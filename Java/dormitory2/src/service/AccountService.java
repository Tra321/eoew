package service;

import model.Account;
import dao.AccountDao;
import dao.impl.AccountDaoImpl;

import java.util.List;
import java.util.Scanner;

public class AccountService {
    private static AccountDao accountDao = new AccountDaoImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void viewAllAccounts(int userId) {
        List<Account> accounts = accountDao.findByUserId(userId);
        if (accounts.isEmpty()) {
            System.out.println("没有找到任何账号记录");
            return;
        }

        System.out.println("\n=== 账号列表 ===");
        for (Account account : accounts) {
            System.out.println("ID: " + account.getId());
            System.out.println("账号名称: " + account.getAccountName());
            System.out.println("用户名: " + account.getUsername());
            System.out.println("密码: " + account.getPassword());
            System.out.println("分类: " + account.getCategory());
            System.out.println("描述: " + account.getDescription());
            System.out.println("创建时间: " + account.getCreateTime());
            System.out.println("更新时间: " + account.getUpdateTime());
            System.out.println("-------------------");
        }
    }

    public static void addAccount(int userId) {
        System.out.println("\n=== 添加新账号 ===");
        System.out.print("请输入账号名称：");
        String accountName = scanner.nextLine();
        
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        
        System.out.print("请输入密码：");
        String password = scanner.nextLine();
        
        System.out.print("请输入分类：");
        String category = scanner.nextLine();
        
        System.out.print("请输入描述：");
        String description = scanner.nextLine();

        Account account = new Account(accountName, username, password, category, description, userId);
        if (accountDao.save(account)) {
            System.out.println("账号添加成功！");
        } else {
            System.out.println("账号添加失败！");
        }
    }

    public static void updateAccount(int userId) {
        System.out.print("请输入要修改的账号ID：");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        Account account = accountDao.findById(accountId);
        if (account == null || account.getUserId() != userId) {
            System.out.println("账号不存在或无权修改！");
            return;
        }

        System.out.println("\n=== 修改账号 ===");
        System.out.print("请输入新的账号名称（直接回车保持原值）：");
        String accountName = scanner.nextLine();
        if (!accountName.isEmpty()) {
            account.setAccountName(accountName);
        }

        System.out.print("请输入新的用户名（直接回车保持原值）：");
        String username = scanner.nextLine();
        if (!username.isEmpty()) {
            account.setUsername(username);
        }

        System.out.print("请输入新的密码（直接回车保持原值）：");
        String password = scanner.nextLine();
        if (!password.isEmpty()) {
            account.setPassword(password);
        }

        System.out.print("请输入新的分类（直接回车保持原值）：");
        String category = scanner.nextLine();
        if (!category.isEmpty()) {
            account.setCategory(category);
        }

        System.out.print("请输入新的描述（直接回车保持原值）：");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            account.setDescription(description);
        }

        if (accountDao.update(account)) {
            System.out.println("账号修改成功！");
        } else {
            System.out.println("账号修改失败！");
        }
    }

    public static void deleteAccount(int userId) {
        System.out.print("请输入要删除的账号ID：");
        int accountId = scanner.nextInt();
        scanner.nextLine();

        Account account = accountDao.findById(accountId);
        if (account == null || account.getUserId() != userId) {
            System.out.println("账号不存在或无权删除！");
            return;
        }

        System.out.print("确定要删除此账号吗？(y/n): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            if (accountDao.delete(accountId)) {
                System.out.println("账号删除成功！");
            } else {
                System.out.println("账号删除失败！");
            }
        }
    }

    public static void searchAccounts(int userId) {
        System.out.println("\n=== 搜索账号 ===");
        System.out.println("1. 按账号名称搜索");
        System.out.println("2. 按分类搜索");
        System.out.println("3. 按描述搜索");
        System.out.print("请选择搜索方式：");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("请输入搜索关键词：");
        String keyword = scanner.nextLine();

        List<Account> results = null;
        switch (choice) {
            case 1:
                results = accountDao.searchByAccountName(userId, keyword);
                break;
            case 2:
                results = accountDao.searchByCategory(userId, keyword);
                break;
            case 3:
                results = accountDao.searchByDescription(userId, keyword);
                break;
            default:
                System.out.println("无效的选择！");
                return;
        }

        if (results.isEmpty()) {
            System.out.println("没有找到匹配的账号");
            return;
        }

        System.out.println("\n=== 搜索结果 ===");
        for (Account account : results) {
            System.out.println("ID: " + account.getId());
            System.out.println("账号名称: " + account.getAccountName());
            System.out.println("用户名: " + account.getUsername());
            System.out.println("密码: " + account.getPassword());
            System.out.println("分类: " + account.getCategory());
            System.out.println("描述: " + account.getDescription());
            System.out.println("-------------------");
        }
    }
} 