package until;

import java.util.Scanner;
import service.AdminService;

public class WarehouseControlMenu {
    public static void ShowMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.仓库新增");
        System.out.println("    2.仓库查看");
        System.out.println("    3.仓库删除");
        System.out.println("    4.仓库修改");
        System.out.println("    5.返回上级菜单");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a){
            case 1:
                AdminService.manAddWarehouse();
                break;
            case 2:
                WarehouseLookMenu.ShowMenu();
                break;
            case 3:
                AdminService.manDeleteWarehouse();
                break;
            case 4:
                AdminService.manUpdateWarehouse();
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