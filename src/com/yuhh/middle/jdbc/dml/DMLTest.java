package com.yuhh.middle.jdbc.dml;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * jdbc操作增改查
 */
public class DMLTest {

    //增加
    @Test
    public void testInsert() {
        String sql = "insert into t_student(name,age) values('阿朱',30);";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        Connection conn = null;
        Statement st = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "root");
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    //更改
    @Test
    public void testUpdate() throws Exception {
        String sql = "update t_student set name='乔峰',age=35 where id=1";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo",
                "root",
                "root");
        Statement st = conn.createStatement();
        st.execute(sql);
        st.close();
        conn.close();
    }

    //删除
    @Test
    public void testDelete() throws Exception {
        String sql = "delete from t_student where id=1";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo",
                "root",
                "root");
        Statement st = conn.createStatement();
        st.execute(sql);
        st.close();
        conn.close();
    }
}
