package until;

import java.util.Scanner;

import service.AdminService;
/**
 * 宿管管理界面
 *
 */
public class ManagerControlMenu {
    public static void ShowMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.宿管新增");
        System.out.println("    2.宿管查看");
        System.out.println("    3.宿管删除");
        System.out.println("    4.宿管修改");
        System.out.println("_________________________");
        int a =sc.nextInt();
        switch(a){
            case 1:
                AdminService.manAddManager();
                break;
            case 2:
                ManagerLookMenu.ShowMenu();
                break;
            case 3:
                AdminService.manDeleteUser();
                break;
            case 4:
                AdminService.manUpdateUser();
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }

}
