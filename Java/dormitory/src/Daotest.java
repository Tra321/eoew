import dao.DormitoryDao;
import dao.impl.DormitoryDaoImpl;

public class Daotest {
    public static void main(String[] args) {
        DormitoryDao dormitoryDao = new DormitoryDaoImpl();
        dormitoryDao.addDormitory("CW2101", "计算系统实训", Float.parseFloat("3.0"));
        dormitoryDao.selectOneDormitory("CW2101");
        dormitoryDao.updateDormitory("CW2101", "数据库原理与应用", Float.parseFloat("3.0"));
        dormitoryDao.selectAllDormitory();
    }
}
