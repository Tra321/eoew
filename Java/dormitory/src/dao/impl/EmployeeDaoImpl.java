package dao.impl;

import dao.EmployeeDao;
import model.Employee;
import until.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public Employee login(Employee employee) {
        String sql = "SELECT * FROM employee WHERE employee_id = ? AND employee_password = ?";
        return BaseDao.executeQuery(rs -> {
            if (rs.next()) {
                return mapEmployee(rs);
            }
            return null;
        }, sql, employee.getEmployeeId(), employee.getEmployeePassword());
    }

    @Override
    public boolean changePassword(String employeeId, String newPassword) {
        String sql = "UPDATE employee SET employee_password = ? WHERE employee_id = ?";
        return BaseDao.executeUpdate(sql, newPassword, employeeId) > 0;
    }

    @Override
    public Employee findById(String employeeId) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        return BaseDao.executeQuery(rs -> {
            if (rs.next()) {
                return mapEmployee(rs);
            }
            return null;
        }, sql, employeeId);
    }

    @Override
    public boolean add(Employee employee) {
        String sql = "INSERT INTO employee (employee_id, employee_name, employee_password, department, position, phone, email, hire_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return BaseDao.executeUpdate(sql, 
                employee.getEmployeeId(), 
                employee.getEmployeeName(), 
                employee.getEmployeePassword(),
                employee.getDepartment(),
                employee.getPosition(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getHireDate()) > 0;
    }

    @Override
    public boolean update(Employee employee) {
        String sql = "UPDATE employee SET employee_name = ?, department = ?, position = ?, " +
                     "phone = ?, email = ? WHERE employee_id = ?";
        return BaseDao.executeUpdate(sql, 
                employee.getEmployeeName(),
                employee.getDepartment(),
                employee.getPosition(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getEmployeeId()) > 0;
    }

    @Override
    public boolean delete(String employeeId) {
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        return BaseDao.executeUpdate(sql, employeeId) > 0;
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employee";
        return BaseDao.executeQuery(rs -> {
            List<Employee> employees = new ArrayList<>();
            while (rs.next()) {
                employees.add(mapEmployee(rs));
            }
            return employees;
        }, sql);
    }
    
    /**
     * 从ResultSet映射为Employee对象
     */
    private Employee mapEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getString("employee_id"));
        employee.setEmployeeName(rs.getString("employee_name"));
        employee.setEmployeePassword(rs.getString("employee_password"));
        employee.setDepartment(rs.getString("department"));
        employee.setPosition(rs.getString("position"));
        employee.setPhone(rs.getString("phone"));
        employee.setEmail(rs.getString("email"));
        
        // 处理日期
        java.sql.Date hireDate = rs.getDate("hire_date");
        if (hireDate != null) {
            employee.setHireDate(hireDate.toLocalDate());
        }
        
        return employee;
    }
} 