package dao;

import model.Class;

public interface ClassDao {
    public void addClass(Class clazz); // 添加班级
    public void deleteClass(String classID); // 删除班级
    public void updateClass(Class clazz); // 更新班级信息
    public Class selectOneClass(String classID); // 查询一个班级信息
    public void selectAllClass(); // 查询所有班级信息
} 