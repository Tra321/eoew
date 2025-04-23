package until;

import java.util.Scanner;
import service.*;
/**
 * 学生界面
 *
 */
public class StuMenu {
    public static void showMenu(String studentID){
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________");
        System.out.println("      欢迎" + studentID + "使用学生考勤管理系统         ");
        System.out.println("    1.查看个人信息");
        System.out.println("    2.修改密码");
        System.out.println("    3.更新个人信息");
        System.out.println("    4.查看个人考勤记录");
        System.out.println("    5.查看考勤统计");
        System.out.println("    6.查看班级同学");
        System.out.println("    7.退出系统");
        System.out.println("_________________________");
        int a = sc.nextInt();
        switch(a){
            case 1:  
                StudentService.viewStudentInfo(studentID);
                break;
            case 2:  
                StudentService.studentChangePassword(studentID);
                break;
            case 3: 
                StudentService.updateStudentProfile(studentID);
                break;
            case 4:     
                StudentService.viewAttendanceRecords(studentID);
                break;
            case 5:     
                StudentService.viewAttendanceStatistics(studentID);
                break;
            case 6:     
                StudentService.viewClassmates(studentID);
                break;
            case 7:  
                System.out.println("用户成功退出！");
                System.exit(0);
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }
}
