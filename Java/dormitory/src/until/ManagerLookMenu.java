package until;
import java.util.Scanner;

import service.AdminService;
/**
 * 宿管信息查看界面
 *
 */
public class ManagerLookMenu {
    public static void ShowMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.查看某一宿管");
        System.out.println("    2.查看全部宿管");
        System.out.println("_________________________");
        int a =sc.nextInt();
        switch(a){
            case 1:
                AdminService.manOneManager();
                break;
            case 2:
                AdminService.manAllManager();
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }
}
