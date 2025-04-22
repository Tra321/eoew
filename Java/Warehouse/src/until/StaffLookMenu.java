package until;

import java.util.Scanner;
import service.StaffMemberService;

public class StaffLookMenu {
    public static void ShowMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.查看单个物品");
        System.out.println("    2.查看所有物品");
        System.out.println("    3.按类别查询物品");
        System.out.println("    4.按仓库查询物品");
        System.out.println("    5.返回上级菜单");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a) {
            case 1:
                StaffMemberService.staffOneItem();
                break;
            case 2:
                StaffMemberService.staffAllItem();
                break;
            case 3:
                StaffMemberService.staffSearchItemByCategory();
                break;
            case 4:
                StaffMemberService.staffSearchItemByWarehouse();
                break;
            case 5:
                StaffMenu.ShowMenu();
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }
} 