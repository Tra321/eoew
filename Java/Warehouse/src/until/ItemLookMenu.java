package until;

import java.util.Scanner;
import service.AdminService;

public class ItemLookMenu {
    public static void ShowMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.查看单个物品");
        System.out.println("    2.查看所有物品");
        System.out.println("    3.返回上级菜单");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a) {
            case 1:
                AdminService.manOneItem();
                break;
            case 2:
                AdminService.manAllItem();
                break;
            case 3:
                ItemControlMenu.ShowMenu();
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }
} 