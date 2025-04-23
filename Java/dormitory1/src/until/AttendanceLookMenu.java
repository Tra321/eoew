package until;

import java.util.Scanner;
import service.AdminService;

/**
 * 考勤记录查看界面
 */
public class AttendanceLookMenu {
    public static void ShowMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("    1.查看某学生考勤记录");
        System.out.println("    2.查看某日期考勤记录");
        System.out.println("    3.查看某班级某日期考勤记录");
        System.out.println("    4.返回上级菜单");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a) {
            case 1:
                AdminService.viewStudentAttendance();
                break;
            case 2:
                AdminService.viewDateAttendance();
                break;
            case 3:
                AdminService.viewClassAttendance();
                break;
            case 4:
                AttendanceControlMenu.ShowMenu();
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }
} 