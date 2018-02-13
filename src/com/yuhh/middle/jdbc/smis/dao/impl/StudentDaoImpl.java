package com.yuhh.middle.jdbc.smis.dao.impl;

import com.yuhh.middle.jdbc.smis.dao.IStudentDao;
import com.yuhh.middle.jdbc.smis.domain.Student;
import com.yuhh.middle.jdbc.smis.handler.BeanHandler;
import com.yuhh.middle.jdbc.smis.handler.BeanListHandler;
import com.yuhh.middle.jdbc.smis.util.JdbcUtil;
import com.yuhh.middle.jdbc.template.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {
    @Override
    public void save(Student student) {
        //insert into t_student(name,age) values('student.getName()','sutdent.getAge()');
        String sql = "insert into t_student(name,age) values(?,?);";
//        Connection conn = JdbcUtil.getConn();
//        PreparedStatement ps = null;
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, student.getName());
//            ps.setInt(2, student.getAge());
//            ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtil.close(conn, ps);
//        }
        JdbcTemplate.update(sql, student.getName(), student.getAge());
    }

    @Override
    public void delete(Long id) {
        //update t_student set name=newStu.getName(),age=newStu.getAge where id=id;
        String sql = "delete from t_student where id=?;";
//        Connection conn = null;
//        Statement st = null;
//        conn = JdbcUtil.getConn();
//        try {
//            st = conn.createStatement();
//            st.execute(sql.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtil.close(conn, st);
//        }
        JdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Long id, Student newStu) {
        //update t_student set name=newStu.getName(),age=newStu.getAge where id=id;
        String sql = "update t_student set name=?,age=? where id=?";
        JdbcTemplate.update(sql, newStu.getName(), newStu.getAge(), id);
    }

    @Override
    public Student get(Long id) {
        String sql = "select * from t_student where id=?;";
        Student student = JdbcTemplate.query(sql, new BeanHandler<>(Student.class), id);
        return student;
    }

    @Override
    public List<Student> list() {
        String sql = "select * from t_student";
        List list = JdbcTemplate.query(sql, new BeanListHandler<>(Student.class));
        return list;
    }
}
