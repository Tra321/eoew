package until;

import java.util.Scanner;
import service.StaffMemberService;

public class StaffMenu {
    public static void ShowMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("      欢迎使用仓库管理系统-员工界面         ");
        System.out.println("    1.查看物品");
        System.out.println("    2.物品操作");
        System.out.println("    3.个人信息");
        System.out.println("    4.退出系统");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a) {
            case 1:
                StaffLookMenu.ShowMenu();
                break;
            case 2:
                StaffItemControlMenu.ShowMenu();
                break;
            case 3:
                StaffMemberService.staffUpdateInfo();
                break;
            case 4:
                System.out.println("用户成功退出！");
                System.exit(0);
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }
} 