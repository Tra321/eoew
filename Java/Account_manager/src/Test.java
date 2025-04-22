
import java.lang.String;
import service.AdminService;
import service.StudentService;
import service.ManagerService;


import java.util.Scanner;

/**
 * 2020/6/30 1.先输入int后输入String时，String类型接收不到（将sc.nextLine()改为sc.next()即可）
 *           2.登录名改为String的studentName可以连接成功，使用int型的studentID报错且显示用户名或密码错误
 *           3.admin表作为管理员表，删除adminRole属性
 *           4.将studentTel以及managerTel数据类型更改为Long
 *           5.student表完成宿舍管理，退课，查询（dormitory表缺少授课宿管，根据宿舍ID自己查去），修改个人信息的基本功能代码（未测试）
 *           6.admin表对学生宿舍的操作功能基本实现（未测试）
 *           7.明日完成managerDaoy和ManagerDaoImpl,ManagerService,ManagerMenu
 *
 */
public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        System.out.println("欢迎使用宿舍管理系统");
        System.out.println("请输入登录名: ");
        String ID = sc.next();

        System.out.println("请输入密码：");
        String password = sc.next();
        System.out.println("请选择您的身份");
        System.out.println("1.管理员");
        System.out.println("2.学生");
        System.out.println("3.宿管");
        int a = sc.nextInt();
        switch(a){
            case 1:
                new AdminService().manLogin(ID,password);
                break;
            case 2:
                new StudentService().studentLogin(ID,password);
                break;
            case 3:
                new ManagerService().managerLogin(ID,password);
            default:
                System.out.println("输入数字不合法，程序退出");
                System.exit(0);
        }
    }

}