package dao;

import model.Manager;

public interface ManagerDao {
    public Manager login(Manager manager);//查询自己的基本信息
    public void changePass(Manager manager);//更换学号以及密码
    public void getLiveDormitoryFloor(String managerID);//录入所教宿舍学生楼层
    public void getAllDormitoryFloor(String managerID);//输出所教宿舍楼层单

}
