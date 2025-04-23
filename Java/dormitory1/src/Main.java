import java.util.Scanner;
import service.AdminService;
import service.StudentService;

/**
 * 学生考勤管理系统主入口
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("      学生考勤管理系统");
        System.out.println("=================================");
        
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("\n请选择登录身份：");
            System.out.println("1. 管理员登录");
            System.out.println("2. 学生登录");
            System.out.println("3. 退出系统");
            System.out.print("请输入选择(1-3): ");
            
            try {
                int choice = sc.nextInt();
                sc.nextLine(); // 清除换行符
                
                switch (choice) {
                    case 1:
                        adminLogin();
                        break;
                    case 2:
                        studentLogin();
                        break;
                    case 3:
                        running = false;
                        System.out.println("感谢使用学生考勤管理系统，再见！");
                        break;
                    default:
                        System.out.println("无效选择，请重新输入！");
                }
            } catch (Exception e) {
                System.out.println("输入错误，请输入数字！");
                sc.nextLine(); // 清除错误输入
            }
        }
        
        sc.close();
    }
    
    /**
     * 管理员登录
     */
    private static void adminLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== 管理员登录 ===");
        System.out.print("请输入管理员ID: ");
        String adminID = sc.nextLine();
        System.out.print("请输入密码: ");
        String password = sc.nextLine();
        
        AdminService adminService = new AdminService();
        adminService.adminLogin(adminID, password);
    }
    
    /**
     * 学生登录
     */
    private static void studentLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=== 学生登录 ===");
        System.out.print("请输入学号: ");
        String studentID = sc.nextLine();
        System.out.print("请输入密码: ");
        String password = sc.nextLine();
        
        StudentService studentService = new StudentService();
        studentService.studentLogin(studentID, password);
    }
} 