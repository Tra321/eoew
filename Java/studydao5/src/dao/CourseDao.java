package dao;

import model.course;

public interface CourseDao {
    public void addCourse(String courseID, String courseName, float courseCredit);//增加课程
    public void deleteCourse(String courseID);//删除课程
    public void updateCourse(String courseID, String courseName, float courseCredit);//更新课程
    public void selectOneCourse(String courseID);//查询一个课程
    public void selectAllCourse();//查询所有课程
}
