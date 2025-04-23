package dao;

import model.Employee;

public interface EmployeeDao {
    Employee login(Employee employee);
    boolean changePassword(String employeeId, String newPassword);
    Employee findById(String employeeId);
    boolean add(Employee employee);
    boolean update(Employee employee);
    boolean delete(String employeeId);
    java.util.List<Employee> findAll();
}