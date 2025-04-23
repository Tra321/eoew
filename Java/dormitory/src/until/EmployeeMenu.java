package until;

import java.util.Scanner;
import service.EmployeeService;
import service.AttendanceService;
import service.LeaveService;

public class EmployeeMenu {
    static Scanner sc = new Scanner(System.in);
    
    public static void showMenu(String employeeId) {
        System.out.println("_________________________");
        System.out.println("      欢迎" + employeeId + "使用员工考勤系统         ");
        System.out.println("    1.签到");
        System.out.println("    2.签退");
        System.out.println("    3.查看考勤记录");
        System.out.println("    4.提交请假申请");
        System.out.println("    5.查看请假记录");
        System.out.println("    6.修改密码");
        System.out.println("    7.退出登录");
        System.out.println("_________________________");
        
        int choice = sc.nextInt();
        sc.nextLine(); // 消耗换行符
        
        EmployeeService employeeService = new EmployeeService();
        AttendanceService attendanceService = new AttendanceService();
        LeaveService leaveService = new LeaveService();
        
        switch(choice) {
            case 1:
                attendanceService.checkIn(employeeId);
                break;
            case 2:
                attendanceService.checkOut(employeeId);
                break;
            case 3:
                attendanceService.viewAttendanceRecords(employeeId);
                break;
            case 4:
                leaveService.submitLeaveRequest(employeeId);
                break;
            case 5:
                leaveService.viewMyLeaveRequests(employeeId);
                break;
            case 6:
                employeeService.changePassword(employeeId);
                break;
            case 7:
                System.out.println("退出成功");
                LoginMenu.showMenu();
                break;
            default:
                System.out.println("输入无效，请重新选择");
                showMenu(employeeId);
                break;
        }
    }
}