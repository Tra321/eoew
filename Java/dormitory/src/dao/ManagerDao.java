package dao;

import model.Manager;

public interface ManagerDao {
    public Manager login(Manager manager);
    public void changePass(Manager manager);
    public void getLiveDormitoryFloor(String managerID);
    public void getAllDormitoryFloor(String managerID);
    public void addStudentLive(String managerID, String studentID, String dormitoryID, float dormitoryFloor);
    public void addInspection(String managerID, String dormitoryID, String inspectionDate, String remark);
}