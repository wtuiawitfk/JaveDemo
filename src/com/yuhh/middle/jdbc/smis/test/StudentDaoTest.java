package com.yuhh.middle.jdbc.smis.test;

import com.yuhh.middle.jdbc.smis.dao.IStudentDao;
import com.yuhh.middle.jdbc.smis.dao.impl.StudentDaoImpl;
import com.yuhh.middle.jdbc.smis.domain.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoTest {
    private IStudentDao dao = new StudentDaoImpl();//依赖dao实现类

    @Test
    void save() {
        Student student = new Student("大鱼儿", 43);
        dao.save(student);
    }

    @Test
    void delete() {
        dao.delete(3L);
    }

    @Test
    void update() {
        Student student = new Student("乔峰", 38);
        dao.update(2L,student);
    }

    @Test
    void get() {
        Student student = dao.get(2L);
        System.out.println(student);
    }

    @Test
    void list() {
        List<Student> list = dao.list();
        for (Student stu :
                list) {
            System.out.println(stu);
        }
    }
}