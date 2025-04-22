package until;

import java.util.Scanner;
import service.AdminService;

public class ItemControlMenu {
    public static void ShowMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.物品新增");
        System.out.println("    2.物品查看");
        System.out.println("    3.物品删除");
        System.out.println("    4.物品修改");
        System.out.println("    5.返回上级菜单");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a) {
            case 1:
                AdminService.manAddItem();
                break;
            case 2:
                ItemLookMenu.ShowMenu();
                break;
            case 3:
                AdminService.manDeleteItem();
                break;
            case 4:
                AdminService.manUpdateItem();
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