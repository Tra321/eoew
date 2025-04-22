package dao;

import model.student;

public interface StudentDao {
    public student login(student student);//查询自己的基本信息
    public void changePass(student student);//更换学号以及密码
    public void getStuDormitory(String studentID);//查询已选宿舍
    public void getStuDormitoryFloor(String studentID);//查询获得学分
    public void getAvaDormitory(String studentID);//查询可选宿舍
    public void chooseDormitory(String studentID, String dormitoryID, float dormitoryFloor);//宿舍管理
    public void deleteDormitory(String studentID, String dormitoryID);//删除宿舍管理
    public void printStuDormitoryFloor(String studentID);//打印个人成绩单
}
