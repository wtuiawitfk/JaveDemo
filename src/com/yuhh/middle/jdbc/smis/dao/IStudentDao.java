package com.yuhh.middle.jdbc.smis.dao;

import com.yuhh.middle.jdbc.smis.domain.Student;

import java.util.List;

/**
 * student类的操作接口
 */
public interface IStudentDao {
    /**
     * 增加一个学生
     * @param student：一个学生对象
     */
    void save(Student student);

    /**
     * 删除指定的学生
     * @param id：学生ID
     */
    void delete(Long id);

    /***
     * 更改指定的学生信息
     * @param id：要更改的学生id
     * @param newStu：新的学生对象
     */
    void update(Long id, Student newStu);

    /***
     * 获取指定的学生对象
     * @param id：学生id
     * @return：一个学生对象
     */
    Student get(Long id);

    /**
     * 获取全部的学生信息
     * @return：全部的学生信息集合
     */
    List<Student> list();
}
