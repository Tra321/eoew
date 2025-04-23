package until;

import java.util.Scanner;
import service.AdminService;

/**
 * 班级管理界面
 */
public class ClassControlMenu {
    public static void ShowMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.添加班级");
        System.out.println("    2.查看班级");
        System.out.println("    3.删除班级");
        System.out.println("    4.修改班级");
        System.out.println("    5.返回上级菜单");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a) {
            case 1:
                AdminService.addClass();
                break;
            case 2:
                ClassLookMenu.ShowMenu();
                break;
            case 3:
                AdminService.deleteClass();
                break;
            case 4:
                AdminService.updateClass();
                break;
            case 5:
                AdminMenu.ShowMenu();
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }
} 