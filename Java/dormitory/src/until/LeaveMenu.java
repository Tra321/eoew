package until;

import java.util.Scanner;
import service.LeaveService;

/**
 * 请假管理菜单
 */
public class LeaveMenu {
    static Scanner sc = new Scanner(System.in);
    
    /**
     * 显示请假管理菜单（管理员视图）
     * @param adminId 管理员ID
     */
    public static void showAdminMenu(String adminId) {
        System.out.println("_________________________");
        System.out.println("      请假管理菜单         ");
        System.out.println("    1.查看待审批请假申请");
        System.out.println("    2.审批请假申请");
        System.out.println("    3.查看历史请假记录");
        System.out.println("    4.导出请假报表");
        System.out.println("    5.返回上级菜单");
        System.out.println("_________________________");
        
        int choice = sc.nextInt();
        sc.nextLine(); // 消耗换行符
        
        LeaveService leaveService = new LeaveService();
        
        switch(choice) {
            case 1:
                // 查看待审批的请假申请
                System.out.println("待审批的请假申请：");
                leaveService.approveLeaveRequests(); // 简化处理，直接调用审批方法
                showAdminMenu(adminId);
                break;
            case 2:
                // 审批请假申请
                leaveService.approveLeaveRequests();
                showAdminMenu(adminId);
                break;
            case 3:
                // 查看历史请假记录
                System.out.println("请输入员工ID：");
                String employeeId = sc.nextLine();
                leaveService.viewMyLeaveRequests(employeeId);
                showAdminMenu(adminId);
                break;
            case 4:
                // 导出请假报表
                // TODO: 调用导出方法
                showAdminMenu(adminId);
                break;
            case 5:
                // 返回上级菜单
                AdminMenu.showMenu(adminId);
                break;
            default:
                System.out.println("输入无效，请重新选择");
                showAdminMenu(adminId);
                break;
        }
    }
    
    /**
     * 显示请假管理菜单（员工视图）
     * @param employeeId 员工ID
     */
    public static void showEmployeeMenu(String employeeId) {
        System.out.println("_________________________");
        System.out.println("      请假申请菜单         ");
        System.out.println("    1.提交请假申请");
        System.out.println("    2.查看我的请假记录");
        System.out.println("    3.返回上级菜单");
        System.out.println("_________________________");
        
        int choice = sc.nextInt();
        sc.nextLine(); // 消耗换行符
        
        LeaveService leaveService = new LeaveService();
        
        switch(choice) {
            case 1:
                // 提交请假申请
                leaveService.submitLeaveRequest(employeeId);
                break;
            case 2:
                // 查看我的请假记录
                leaveService.viewMyLeaveRequests(employeeId);
                break;
            case 3:
                // 返回上级菜单
                EmployeeMenu.showMenu(employeeId);
                break;
            default:
                System.out.println("输入无效，请重新选择");
                showEmployeeMenu(employeeId);
                break;
        }
    }
} 