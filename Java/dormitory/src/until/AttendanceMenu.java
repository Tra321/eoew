package until;

import java.util.Scanner;
import service.AttendanceService;

/**
 * 考勤管理菜单
 */
public class AttendanceMenu {
    static Scanner sc = new Scanner(System.in);
    
    /**
     * 显示考勤管理菜单
     * @param adminId 管理员ID
     */
    public static void showMenu(String adminId) {
        System.out.println("_________________________");
        System.out.println("      考勤管理菜单         ");
        System.out.println("    1.查看考勤记录");
        System.out.println("    2.异常考勤审核");
        System.out.println("    3.导出考勤报表");
        System.out.println("    4.返回上级菜单");
        System.out.println("_________________________");
        
        int choice = sc.nextInt();
        sc.nextLine(); // 消耗换行符
        
        AttendanceService attendanceService = new AttendanceService();
        
        switch(choice) {
            case 1:
                // 查看指定员工的考勤记录
                System.out.println("请输入员工ID：");
                String employeeId = sc.nextLine();
                attendanceService.viewAttendanceRecords(employeeId);
                break;
            case 2:
                // 异常考勤审核
                attendanceService.reviewAbnormalAttendance();
                showMenu(adminId);
                break;
            case 3:
                // 导出考勤报表
                System.out.println("请输入员工ID（留空导出全部）：");
                String empId = sc.nextLine();
                // TODO: 调用导出方法
                showMenu(adminId);
                break;
            case 4:
                // 返回上级菜单
                AdminMenu.showMenu(adminId);
                break;
            default:
                System.out.println("输入无效，请重新选择");
                showMenu(adminId);
                break;
        }
    }
} 