package until;

import java.util.Scanner;
import service.AdminService;

public class WarehouseLookMenu {
    public static void ShowMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.查看单个仓库");
        System.out.println("    2.查看所有仓库");
        System.out.println("    3.返回上级菜单");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a) {
            case 1:
                AdminService.manOneWarehouse();
                break;
            case 2:
                AdminService.manAllWarehouse();
                break;
            case 3:
                WarehouseControlMenu.ShowMenu();
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }
} 