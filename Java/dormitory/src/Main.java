import until.LoginMenu;

/**
 * 员工考勤系统主程序入口
 */
public class Main {
    /**
     * 程序入口方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        System.out.println("欢迎使用员工考勤管理系统");
        
        // 显示登录菜单
        LoginMenu.showMenu();
    }
} 