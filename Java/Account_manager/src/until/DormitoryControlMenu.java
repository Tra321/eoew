package until;

import java.util.Scanner;
/**
 * 宿舍管理界面
 */
import service.AdminService;
public class DormitoryControlMenu {
    public static void ShowMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.宿舍新增");
        System.out.println("    2.宿舍查看");
        System.out.println("    3.宿舍删除");
        System.out.println("    4.宿舍修改");
        System.out.println("_________________________");
        int a =sc.nextInt();
        switch(a){
            case 1:
                AdminService.manAddDormitory();
                break;
            case 2:
                DormitoryLookMenu.ShowMenu();
                break;
            case 3:
                AdminService.manDeleteDormitory();
                break;
            case 4:
                AdminService.manUpdateDormitory();
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);

        }
    }

}
