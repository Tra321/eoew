package until;

import java.util.Scanner;
import service.StaffMemberService;

public class StaffItemControlMenu {
    public static void ShowMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.更新物品数量");
        System.out.println("    2.返回上级菜单");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a) {
            case 1:
                StaffMemberService.staffUpdateItemQuantity();
                break;
            case 2:
                StaffMenu.ShowMenu();
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }
} 