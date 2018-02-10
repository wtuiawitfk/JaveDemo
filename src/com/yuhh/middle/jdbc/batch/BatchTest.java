package com.yuhh.middle.jdbc.batch;

import com.yuhh.middle.jdbc.smis.util.JdbcUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BatchTest {
    //对此使用批处理执行效率
    @Test
        //不使用批处理：2741ms
        //rewriteBatchedStatements=true:2722
    void test1() throws Exception {
        String sql = "insert into t_student(name,age) values('A',?);";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 5000; i++) {
            ps.setInt(1, i);
            ps.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        JdbcUtil.close(conn, ps);
    }

    @Test
        //使用批处理：2714ms
        //rewriteBatchedStatements=true:
    void test2() throws Exception {
        String sql = "insert into t_student(name,age) values('A',?);";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 5000; i++) {
            ps.setInt(1, i);
            ps.addBatch();
            if (i % 200 == 0) {
                ps.executeBatch();
                ps.clearParameters();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        JdbcUtil.close(conn, ps);
    }
}
