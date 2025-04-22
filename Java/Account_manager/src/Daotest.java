import dao.DormitoryDao;
import dao.impl.DormitoryDaoImpl;

public class Daotest {
    public static void main(String[] args) {
        DormitoryDao dormitoryDao = new DormitoryDaoImpl();
        dormitoryDao.addDormitory("201", "黄槐楼", 3);
        dormitoryDao.selectOneDormitory("201");
        dormitoryDao.updateDormitory("201", "红棉楼", 3);
        dormitoryDao.selectAllDormitory();
    }
}
