import java.lang.String;
import service.AdminService;
import service.StaffMemberService;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎使用仓库管理系统");
        System.out.println("请输入登录名: ");
        String ID = sc.next();

        System.out.println("请输入密码：");
        String password = sc.next();
        System.out.println("请选择您的身份");
        System.out.println("1.管理员");
        System.out.println("2.仓库员工");
        int a = sc.nextInt();
        switch(a){
            case 1:
                new AdminService().manLogin(ID, password);
                break;
            case 2:
                new StaffMemberService().staffLogin(ID, password);
                break;
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }
}