package service;

import dao.AdminDao;
import dao.EmployeeDao;
import dao.AttendanceRecordDao;
import dao.LeaveRequestDao;
import dao.impl.AdminDaoImpl;
import dao.impl.EmployeeDaoImpl;
import dao.impl.AttendanceRecordDaoImpl;
import dao.impl.LeaveRequestDaoImpl;
import model.Admin;
import model.Employee;
import model.AttendanceRecord;
import model.LeaveRequest;
import until.AdminMenu;
import until.ExportUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    private AdminDao adminDao = new AdminDaoImpl();
    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private AttendanceRecordDao attendanceDao = new AttendanceRecordDaoImpl();
    private LeaveRequestDao leaveRequestDao = new LeaveRequestDaoImpl();
    private Scanner sc = new Scanner(System.in);
    
    /**
     * 管理员登录
     */
    public void login(String adminId, String password) {
        Admin admin = new Admin();
        admin.setAdminId(adminId);
        admin.setAdminPassword(password);
        
        Admin loggedAdmin = adminDao.login(admin);
        if (loggedAdmin != null) {
            System.out.println("登录成功");
            AdminMenu.showMenu(adminId);
        } else {
            System.out.println("管理员ID或密码错误");
        }
    }
    
    /**
     * 修改管理员密码
     */
    public void changePassword(String adminId) {
        System.out.println("请输入新密码：");
        String newPassword = sc.nextLine();
        
        if (newPassword.isEmpty() || newPassword.length() > 20) {
            System.out.println("密码不符合要求");
            return;
        }
        
        if (adminDao.changePassword(adminId, newPassword)) {
            System.out.println("密码修改成功");
        } else {
            System.out.println("密码修改失败");
        }
        
        AdminMenu.showMenu(adminId);
    }
    
    /**
     * 添加员工
     */
    public void addEmployee() {
        Employee employee = new Employee();
        
        System.out.println("请输入员工ID：");
        String employeeId = sc.nextLine();
        employee.setEmployeeId(employeeId);
        
        System.out.println("请输入员工姓名：");
        String employeeName = sc.nextLine();
        employee.setEmployeeName(employeeName);
        
        System.out.println("请输入初始密码：");
        String password = sc.nextLine();
        employee.setEmployeePassword(password);
        
        System.out.println("请输入所属部门：");
        String department = sc.nextLine();
        employee.setDepartment(department);
        
        System.out.println("请输入职位：");
        String position = sc.nextLine();
        employee.setPosition(position);
        
        System.out.println("请输入联系电话：");
        String phone = sc.nextLine();
        employee.setPhone(phone);
        
        System.out.println("请输入邮箱：");
        String email = sc.nextLine();
        employee.setEmail(email);
        
        employee.setHireDate(LocalDate.now());
        
        if (employeeDao.add(employee)) {
            System.out.println("员工添加成功");
        } else {
            System.out.println("员工添加失败");
        }
        
        AdminMenu.showMenu(null);
    }
    
    /**
     * 修改员工信息
     */
    public void updateEmployee() {
        System.out.println("请输入要修改的员工ID：");
        String employeeId = sc.nextLine();
        
        Employee employee = employeeDao.findById(employeeId);
        if (employee == null) {
            System.out.println("员工不存在");
            AdminMenu.showMenu(null);
            return;
        }
        
        System.out.println("请输入员工姓名（原值：" + employee.getEmployeeName() + "）：");
        String employeeName = sc.nextLine();
        if (!employeeName.isEmpty()) {
            employee.setEmployeeName(employeeName);
        }
        
        System.out.println("请输入所属部门（原值：" + employee.getDepartment() + "）：");
        String department = sc.nextLine();
        if (!department.isEmpty()) {
            employee.setDepartment(department);
        }
        
        System.out.println("请输入职位（原值：" + employee.getPosition() + "）：");
        String position = sc.nextLine();
        if (!position.isEmpty()) {
            employee.setPosition(position);
        }
        
        System.out.println("请输入联系电话（原值：" + employee.getPhone() + "）：");
        String phone = sc.nextLine();
        if (!phone.isEmpty()) {
            employee.setPhone(phone);
        }
        
        System.out.println("请输入邮箱（原值：" + employee.getEmail() + "）：");
        String email = sc.nextLine();
        if (!email.isEmpty()) {
            employee.setEmail(email);
        }
        
        if (employeeDao.update(employee)) {
            System.out.println("员工信息更新成功");
        } else {
            System.out.println("员工信息更新失败");
        }
        
        AdminMenu.showMenu(null);
    }
    
    /**
     * 删除员工
     */
    public void deleteEmployee() {
        System.out.println("请输入要删除的员工ID：");
        String employeeId = sc.nextLine();
        
        System.out.println("确认删除员工（Y/N）：");
        String confirm = sc.nextLine();
        
        if ("Y".equalsIgnoreCase(confirm)) {
            if (employeeDao.delete(employeeId)) {
                System.out.println("员工删除成功");
            } else {
                System.out.println("员工删除失败");
            }
        } else {
            System.out.println("已取消删除操作");
        }
        
        AdminMenu.showMenu(null);
    }
    
    /**
     * 查看所有员工
     */
    public void viewAllEmployees() {
        List<Employee> employees = employeeDao.findAll();
        
        if (employees.isEmpty()) {
            System.out.println("没有员工记录");
            AdminMenu.showMenu(null);
            return;
        }
        
        System.out.println("员工列表：");
        System.out.println("ID\t姓名\t\t部门\t\t职位\t\t电话\t\t邮箱\t\t入职日期");
        for (Employee employee : employees) {
            System.out.printf("%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\n",
                    employee.getEmployeeId(),
                    employee.getEmployeeName(),
                    employee.getDepartment(),
                    employee.getPosition(),
                    employee.getPhone(),
                    employee.getEmail(),
                    employee.getHireDate());
        }
        
        AdminMenu.showMenu(null);
    }
    
    /**
     * 导出员工考勤报表
     */
    public void exportAttendanceReport() {
        System.out.println("请输入要导出的员工ID（留空导出全部）：");
        String employeeId = sc.nextLine();
        
        System.out.println("请输入开始日期（yyyy-MM-dd）：");
        String startDateStr = sc.nextLine();
        
        System.out.println("请输入结束日期（yyyy-MM-dd）：");
        String endDateStr = sc.nextLine();
        
        try {
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);
            
            List<AttendanceRecord> records;
            if (employeeId.isEmpty()) {
                // 导出所有员工的考勤记录
                records = attendanceDao.getAbnormalAttendance(); // 这里简化处理，实际应该根据日期范围查询所有员工
            } else {
                // 导出指定员工的考勤记录
                records = attendanceDao.getAttendanceByDateRange(employeeId, startDate, endDate);
            }
            
            if (records.isEmpty()) {
                System.out.println("没有符合条件的考勤记录");
            } else {
                ExportUtil.exportAttendanceRecords(records, employeeId.isEmpty() ? "all" : employeeId);
                System.out.println("考勤报表导出成功");
            }
            
        } catch (Exception e) {
            System.out.println("日期格式错误或导出失败：" + e.getMessage());
        }
        
        AdminMenu.showMenu(null);
    }
    
    /**
     * 导出请假统计报表
     */
    public void exportLeaveReport() {
        System.out.println("请输入要导出的员工ID（留空导出全部）：");
        String employeeId = sc.nextLine();
        
        List<LeaveRequest> requests;
        if (employeeId.isEmpty()) {
            // 导出所有员工的请假记录，这里简化处理
            requests = leaveRequestDao.getPendingLeaveRequests();
        } else {
            // 导出指定员工的请假记录
            requests = leaveRequestDao.getLeaveRequestsByEmployee(employeeId);
        }
        
        if (requests.isEmpty()) {
            System.out.println("没有符合条件的请假记录");
        } else {
            ExportUtil.exportLeaveRequests(requests);
            System.out.println("请假报表导出成功");
        }
        
        AdminMenu.showMenu(null);
    }
    
    /**
     * 导出异常考勤报表
     */
    public void exportAbnormalReport() {
        List<AttendanceRecord> records = attendanceDao.getAbnormalAttendance();
        
        if (records.isEmpty()) {
            System.out.println("没有异常考勤记录");
        } else {
            ExportUtil.exportAbnormalAttendance(records);
            System.out.println("异常考勤报表导出成功");
        }
        
        AdminMenu.showMenu(null);
    }
} 