package com.yuhh.middle.jdbc.prepareStatement;

import com.yuhh.middle.jdbc.smis.util.JdbcUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * 测试预作用sql语句操作
 * 插入：花无缺 24
 */
public class PrepareStatementTest {

    @Test
    //使用statement
    void saveTest() throws Exception{
        String sql = "insert into t_student(name,age) values('花无缺',24);";
        Connection conn = JdbcUtil.getConn();
        Statement st = conn.createStatement();
        st.execute(sql);
        JdbcUtil.close(conn, st);
    }

    @Test
        //使用prepareStatement
    void saveTest2() throws Exception {
        String sql = "insert into t_student(name,age) values(?,?);";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "张无忌");
        ps.setInt(2, 23);
        ps.executeUpdate();
        JdbcUtil.close(conn, ps, null);
    }
}
