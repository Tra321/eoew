package service;

import dao.EmployeeDao;
import dao.impl.EmployeeDaoImpl;
import model.Employee;
import until.EmployeeMenu;

public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    
    public void login(String employeeId, String password) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setEmployeePassword(password);
        
        Employee loggedEmployee = employeeDao.login(employee);
        if (loggedEmployee != null) {
            System.out.println("登录成功");
            EmployeeMenu.showMenu(employeeId);
        } else {
            System.out.println("用户名或密码错误");
        }
    }
    
    public void changePassword(String employeeId) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("请输入新密码");
        String newPassword = sc.nextLine();
        
        if (newPassword.isEmpty() || newPassword.length() > 20) {
            System.out.println("密码不符合要求");
            return;
        }
        
        if (employeeDao.changePassword(employeeId, newPassword)) {
            System.out.println("密码修改成功");
        } else {
            System.out.println("密码修改失败");
        }
        
        EmployeeMenu.showMenu(employeeId);
    }
    
    // 其他员工相关方法...
}