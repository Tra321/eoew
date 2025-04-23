package until;

import java.util.Scanner;
import service.AdminService;

/**
 * 考勤管理界面
 */
public class AttendanceControlMenu {
    public static void ShowMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.添加考勤记录");
        System.out.println("    2.查看考勤记录");
        System.out.println("    3.删除考勤记录");
        System.out.println("    4.修改考勤记录");
        System.out.println("    5.生成考勤统计");
        System.out.println("    6.返回上级菜单");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a) {
            case 1:
                AdminService.addAttendance();
                break;
            case 2:
                AttendanceLookMenu.ShowMenu();
                break;
            case 3:
                AdminService.deleteAttendance();
                break;
            case 4:
                AdminService.updateAttendance();
                break;
            case 5:
                AdminService.generateAttendanceStatistics();
                break;
            case 6:
                AdminMenu.ShowMenu();
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }
} 