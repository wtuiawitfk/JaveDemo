package com.yuhh.middle.jdbc.smis.test;

import com.yuhh.middle.jdbc.smis.dao.IStudentDao;
import com.yuhh.middle.jdbc.smis.dao.impl.StudentDaoImpl;
import com.yuhh.middle.jdbc.smis.domain.Student;
import com.yuhh.middle.jdbc.template.HibernateTemplate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoTest {
    private IStudentDao dao = new StudentDaoImpl();//依赖dao实现类

    @Test
    void save() {
        Student student = new Student("小鱼儿", 32);
        //dao.save(student);
        try {
            HibernateTemplate.save(student);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void delete() {
        dao.delete(1L);
    }

    @Test
    void update() {
        Student student = new Student("肖峰", 38);
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