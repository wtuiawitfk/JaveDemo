package com.yuhh.middle.jdbc.ddl_catch;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * jdbc基本操作
 * 1。加载驱动
 * 2.获取连接对象
 * 3.获取操作对象
 * 4.执行SQL语句
 * 5.释放资源
 */

public class DDLTest {

    @Test
    public void test() throws Exception {
//        1。加载驱动
        Class.forName("com.mysql.jdbc.Driver");
//        2.获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "root");
//        3.获取操作对象
        Statement statement = connection.createStatement();
//        4.执行SQL语句
        String sql = "create table t_student(id bigint primary key auto_increment,name VARCHAR(20),age int);";
        statement.execute(sql);
//        5.释放资源
        statement.close();
        connection.close();
    }

    @Test
    public void testHandleException() {
        String sql = "create table t_student(id bigint primary key auto_increment,name VARCHAR(20),age int);";
        Connection conn = null;
        Statement st = null;
        try {
//            1。加载驱动
            Class.forName("com.mysql.jdbc.Driver");
//        2.获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "root");
//        3.获取操作对象
            st = conn.createStatement();
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
