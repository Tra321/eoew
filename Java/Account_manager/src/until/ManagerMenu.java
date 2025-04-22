package until;

import java.util.Scanner;
import service.*;
/**
 * 宿管界面
 *
 */
public class ManagerMenu {
    public static void ShowMenu(String managerID){
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("      欢迎"+managerID+"使用宿舍管理系统         ");
        System.out.println("    1.修改登录密码");
        System.out.println("    2.录入学生住宿楼层");
        System.out.println("    3.打印住宿楼层");
        System.out.println("    4.退出宿舍管理系统");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a){
            case 1:  ManagerService.managerChangePassWord(managerID);
                break;
            case 2:  ManagerService.managerStuFloor(managerID);
                break;
            case 3:     ManagerService.managerAvaFloor(managerID);
                break;
            case 4:  System.out.println("用户成功退出！");
                System.exit(0);
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }


    }
}

