package com.yuhh.middle.jdbc.smis.dao.impl;

import com.yuhh.middle.jdbc.smis.dao.IStudentDao;
import com.yuhh.middle.jdbc.smis.domain.Student;
import com.yuhh.middle.jdbc.smis.util.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {
    @Override
    public void save(Student student) {
        //insert into t_student(name,age) values('student.getName()','sutdent.getAge()');
        String sql = "insert into t_student(name,age) values(?,?);";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps);
        }
    }

    @Override
    public void delete(Long id) {
        //update t_student set name=newStu.getName(),age=newStu.getAge where id=id;
        String sql = "delete from t_student where id=" + id;
        Connection conn = null;
        Statement st = null;
        conn = JdbcUtil.getConn();
        try {
            st = conn.createStatement();
            st.execute(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st);
        }
    }

    @Override
    public void update(Long id, Student newStu) {
        //update t_student set name=newStu.getName(),age=newStu.getAge where id=id;
        String sql = "update t_student set name=?,age=? where id=?";
        System.out.println(sql.toString());
        Connection conn = JdbcUtil.getConn();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, newStu.getName());
            pst.setInt(2, newStu.getAge());
            pst.setLong(3, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, pst);
        }
    }

    @Override
    public Student get(Long id) {
        String sql = "select * from t_student where id=" + id;
        Connection conn = JdbcUtil.getConn();
        Statement st = null;
        ResultSet rs = null;


        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getLong("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));

                return stu;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, rs);
        }
        return null;
    }

    @Override
    public List<Student> list() {
        List<Student> list = new ArrayList<>();
        String sql = "select * from t_student";
        Connection conn = JdbcUtil.getConn();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getLong("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));

                list.add(stu);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, st, rs);
        }

        return null;
    }
}
