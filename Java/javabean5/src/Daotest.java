import dao.CourseDao;
import dao.impl.CourseDaoImpl;
import model.course;

import java.util.List;

public class Daotest {
    public static void main(String[] args) {
        CourseDao courseDao = new CourseDaoImpl();
        course coursebean =new course();
        coursebean.setCourseID("2774");
        coursebean.setCourseName("线性代数");
        coursebean.setCourseCredit(4.0f);
        courseDao.addCourse(coursebean);
        courseDao.selectOneCourse("CW2101");
        coursebean =new course();
        coursebean.setCourseID("1001");
        coursebean.setCourseName("数据库原理与应用");
        coursebean.setCourseCredit(3.0f);
        courseDao.updateCourse(coursebean);
        List<course> courseList = courseDao.selectAllCourse();
        System


        System.out.println("courseID "+"\t"+" courseName"+"\t"+"courseCredit"+"\t");

        System.out.println(courseID+" \t "+courseName+" \t "+courseCredit);
    }
}
