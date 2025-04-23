package dao;

import model.student;

public interface StudentDao {
    public student login(student student); // 学生登录
    public void changePass(student student); // 修改密码
    public void updateProfile(student student); // 更新个人信息
    public student getStudentInfo(String studentID); // 获取学生信息
    public void getStudentsByClass(String classID); // 获取班级所有学生
    public void getAllStudents(); // 获取所有学生
}
