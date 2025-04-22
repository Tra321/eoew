package dao;

public interface DormitoryDao {
    public void addDormitory(String dormitoryID, String dormitoryBuild, float dormitoryFloor);//增加宿舍
    public void deleteDormitory(String dormitoryID);//删除宿舍
    public void updateDormitory(String dormitoryID, String dormitoryBuild, float dormitoryFloor);//更新宿舍
    public void selectOneDormitory(String dormitoryID);//查询一个宿舍
    public void selectAllDormitory();//查询所有宿舍
}
