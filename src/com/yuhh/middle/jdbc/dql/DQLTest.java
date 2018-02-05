package com.yuhh.middle.jdbc.dql;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * jdbc查询的基本操作
 */
public class DQLTest {
    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/jdbcdemo";
    private static String USER = "root";
    private static String PASSWORD = "root";

    @Test
    /**
     * 查询数据条数
     */
    public void testCount() throws Exception{
        String sql = "select count(id) count from t_student;";
        Class.forName(DQLTest.DRIVER);
        Connection conn = DriverManager.getConnection(DQLTest.URL, DQLTest.USER, DQLTest.PASSWORD);
        Statement st = conn.createStatement();
        //获得结果集
        ResultSet resultSet = st.executeQuery(sql);
        while (resultSet.next()) {
            //通过列名拿到值
            long count = resultSet.getLong("count");
            System.out.println(count);
        }
    }

    @Test
    /**
     * 单条查询
     */
    public void testSingle() throws Exception{
        String sql = "select * from t_student where id=2";
        Class.forName(DQLTest.DRIVER);
        Connection conn = DriverManager.getConnection(DQLTest.URL, DQLTest.USER, DQLTest.PASSWORD);
        Statement st = conn.createStatement();
        //获得结果集
        ResultSet resultSet = st.executeQuery(sql);
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            System.out.println(id + "," + name + "," + age);
        }
    }

    /**
     * 全部查询
     */
    @Test
    public void testQueryAll() throws Exception{
        String sql = "select * from t_student";
        Class.forName(DQLTest.DRIVER);
        Connection conn = DriverManager.getConnection(DQLTest.URL, DQLTest.USER, DQLTest.PASSWORD);
        Statement st = conn.createStatement();

        ResultSet resultSet = st.executeQuery(sql);
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            System.out.println(id + "," + name + "," + age);
        }
    }
}
