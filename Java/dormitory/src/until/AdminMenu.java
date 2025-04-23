package until;

import java.util.Scanner;
import service.AdminService;
import service.EmployeeService;
import service.AttendanceService;
import service.LeaveService;

public class AdminMenu {
    static Scanner sc = new Scanner(System.in);
    
    public static void showMenu(String adminId) {
        System.out.println("_________________________");
        System.out.println("      欢迎管理员" + adminId + "使用员工考勤系统         ");
        System.out.println("    1.员工管理");
        System.out.println("    2.考勤审核");
        System.out.println("    3.请假审批");
        System.out.println("    4.导出报表");
        System.out.println("    5.修改密码");
        System.out.println("    6.退出登录");
        System.out.println("_________________________");
        
        int choice = sc.nextInt();
        sc.nextLine(); // 消耗换行符
        
        AdminService adminService = new AdminService();
        AttendanceService attendanceService = new AttendanceService();
        LeaveService leaveService = new LeaveService();
        
        switch(choice) {
            case 1:
                employeeManagement();
                break;
            case 2:
                attendanceService.reviewAbnormalAttendance();
                showMenu(adminId);
                break;
            case 3:
                leaveService.approveLeaveRequests();
                showMenu(adminId);
                break;
            case 4:
                exportReports();
                break;
            case 5:
                adminService.changePassword(adminId);
                break;
            case 6:
                System.out.println("退出成功");
                LoginMenu.showMenu();
                break;
            default:
                System.out.println("输入无效，请重新选择");
                showMenu(adminId);
                break;
        }
    }
    
    private static void employeeManagement() {
        System.out.println("_________________________");
        System.out.println("      员工管理         ");
        System.out.println("    1.添加员工");
        System.out.println("    2.修改员工信息");
        System.out.println("    3.删除员工");
        System.out.println("    4.查看所有员工");
        System.out.println("    5.返回上级菜单");
        System.out.println("_________________________");
        
        int choice = sc.nextInt();
        sc.nextLine(); // 消耗换行符
        
        AdminService adminService = new AdminService();
        
        switch(choice) {
            case 1:
                adminService.addEmployee();
                break;
            case 2:
                adminService.updateEmployee();
                break;
            case 3:
                adminService.deleteEmployee();
                break;
            case 4:
                adminService.viewAllEmployees();
                break;
            case 5:
                // 返回上级菜单
                break;
            default:
                System.out.println("输入无效，请重新选择");
                employeeManagement();
                break;
        }
    }
    
    private static void exportReports() {
        System.out.println("_________________________");
        System.out.println("      导出报表         ");
        System.out.println("    1.导出员工考勤报表");
        System.out.println("    2.导出请假统计报表");
        System.out.println("    3.导出异常考勤报表");
        System.out.println("    4.返回上级菜单");
        System.out.println("_________________________");
        
        int choice = sc.nextInt();
        sc.nextLine(); // 消耗换行符
        
        AdminService adminService = new AdminService();
        
        switch(choice) {
            case 1:
                adminService.exportAttendanceReport();
                break;
            case 2:
                adminService.exportLeaveReport();
                break;
            case 3:
                adminService.exportAbnormalReport();
                break;
            case 4:
                // 返回上级菜单
                break;
            default:
                System.out.println("输入无效，请重新选择");
                exportReports();
                break;
        }
    }
}