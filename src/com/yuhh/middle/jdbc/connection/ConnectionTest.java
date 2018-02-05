package com.yuhh.middle.jdbc.connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTest {

    @Test
    public void test1() throws Exception {
        //加载数据库类
        Class.forName("com.mysql.jdbc.Driver");

        //获得connection对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo",
                "root",
                "root");
        System.out.println(connection);
    }
}
