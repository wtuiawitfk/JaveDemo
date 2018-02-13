package com.yuhh.middle.jdbc.datasource.dbcp;

import com.yuhh.middle.jdbc.smis.util.DBCPUtil;
import com.yuhh.middle.jdbc.smis.util.JdbcUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBCPTest {

    //获取DBCP连接池对象方法
    public DataSource getDataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/jdbcdemo");
        bds.setUsername("root");
        bds.setPassword("root");
        return bds;
    }

    @Test
    void test1() throws Exception {
        //通过连接池对象来获取连接对象
        Connection conn = this.getDataSource().getConnection();
        String sql = "Select * from t_student;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println(name + "," + age);
        }
        JdbcUtil.close(conn, ps, rs);
    }

    @Test
    void test2() throws Exception {
        Connection conn = DBCPUtil.getConn();
        String sql = "Select * from t_student;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println(name + "," + age);
        }
        JdbcUtil.close(conn, ps, rs);
    }
}
