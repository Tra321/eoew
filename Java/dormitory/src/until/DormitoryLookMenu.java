package until;
/**
 * 宿舍查看界面
 */
import java.util.Scanner;
import service.AdminService;
public class DormitoryLookMenu {

    public static void ShowMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.查看某一宿舍");
        System.out.println("    2.查看全部宿舍");
        System.out.println("_________________________");
        int a =sc.nextInt();
        switch(a){
            case 1:
                AdminService.manOneDormitory();
                break;
            case 2:
                AdminService.manAllDormitory();
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }

}
