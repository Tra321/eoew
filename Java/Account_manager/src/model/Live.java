package model;

public class Live {
    private int dormitoryFloor;
    private String studentID;
    private String dormitoryID;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getDormitoryID() {
        return dormitoryID;
    }

    public void setDormitoryID(String dormitoryID) {
        this.dormitoryID = dormitoryID;
    }

    public int getDormitoryFloor() {
        return dormitoryFloor;
    }

    public void setDormitoryFloor(int dormitoryFloor) {
        this.dormitoryFloor = dormitoryFloor;
    }
}
