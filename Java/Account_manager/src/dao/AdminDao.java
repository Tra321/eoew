package dao;

import model.Dormitory;
import model.admin;
import model.student;
import model.Manager;

public interface AdminDao {
    public admin login(admin admin);

    public void addDormitory(Dormitory dormitory);//增加宿舍
    public void deleteDormitory(String dormitoryID);//删除宿舍
    public void updateDormitory(Dormitory dormitory);//更新宿舍
    public void selectOneDormitory(String dormitoryID);//查询一个宿舍
    public void selectAllDormitory();//查询所有宿舍

    public void addStudent(student student);//增加学生信息
    public void deleteStudent(String studentID);//删除学生信息
    public void updateStudent(student student);//更新学生信息
    public void selectOneStudent(String studentID);//查询一个学生信息
    public void selectAllStudent();//查询所有学生信息

    public void addManager(Manager manager);//增加宿管信息
    public void deleteManager(String managerID);//删除宿管信息
    public void updateManager(Manager manager);//更新宿管信息
    public void selectOneManager(String managerID);//查询一个宿管信息
    public void selectAllManager();//查询所有宿管信息

}
