package until;

import java.util.Scanner;
import service.AdminService;

public class StaffControlMenu {
    public static void ShowMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.员工新增");
        System.out.println("    2.员工查看");
        System.out.println("    3.员工删除");
        System.out.println("    4.员工修改");
        System.out.println("    5.返回上级菜单");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a) {
            case 1:
                AdminService.manAddStaff();
                break;
            case 2:
                StaffLookControlMenu.ShowMenu();
                break;
            case 3:
                AdminService.manDeleteStaff();
                break;
            case 4:
                AdminService.manUpdateStaff();
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