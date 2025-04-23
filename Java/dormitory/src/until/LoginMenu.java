package until;

import java.util.Scanner;
import service.EmployeeService;
import service.AdminService;

public class LoginMenu {
    static Scanner sc = new Scanner(System.in);
    
    public static void showMenu() {
        System.out.println("_________________________");
        System.out.println("    欢迎使用员工考勤系统         ");
        System.out.println("     1.员工登录");
        System.out.println("     2.管理员登录");
        System.out.println("     3.退出系统");
        System.out.println("_________________________");
        
        int choice = sc.nextInt();
        sc.nextLine(); // 消耗换行符
        
        switch(choice) {
            case 1:
                employeeLogin();
                break;
            case 2:
                adminLogin();
                break;
            case 3:
                System.out.println("谢谢使用，再见！");
                System.exit(0);
                break;
            default:
                System.out.println("输入无效，请重新选择");
                showMenu();
                break;
        }
    }
    
    private static void employeeLogin() {
        System.out.println("请输入员工ID：");
        String employeeId = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        
        EmployeeService employeeService = new EmployeeService();
        employeeService.login(employeeId, password);
    }
    
    private static void adminLogin() {
        System.out.println("请输入管理员ID：");
        String adminId = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        
        AdminService adminService = new AdminService();
        adminService.login(adminId, password);
    }
}