package dao;

import model.course;

import java.util.List;

public interface CourseDao {
    public void addCourse(course coursebean);//增加课程
    public void deleteCourse(String courseID);//删除课程
    public void updateCourse(course coursebean);//更新课程
    public void selectOneCourse(String courseID);//查询一个课程
    public List<course> selectAllCourse();//查询所有课程
}
