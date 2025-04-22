import dao.Study5Dao;
import dao.impl.Study5DaoImpl;

public class Study5DaoTest {
    public static void main(String[] args) {
        Study5Dao study5Dao = new Study5DaoImpl();
        study5Dao.addCourse("KC001", "计算系统实训", 1.0f);
        study5Dao.deleteCourse("KC001");
        study5Dao.updateCourse("1001", "Java EE开发技术", 4.0f);
        study5Dao.selectAllCourse();
    }
}
