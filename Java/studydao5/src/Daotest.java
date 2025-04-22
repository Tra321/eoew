import dao.CourseDao;
import dao.impl.CourseDaoImpl;

public class Daotest {
    public static void main(String[] args) {
        CourseDao courseDao = new CourseDaoImpl();
        courseDao.addCourse("CW2101", "计算系统实训", Float.parseFloat("3.0"));
        courseDao.selectOneCourse("CW2101");
        courseDao.updateCourse("CW2101", "数据库原理与应用", Float.parseFloat("3.0"));
        courseDao.selectAllCourse();
    }
}
