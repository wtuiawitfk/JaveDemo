package com.yuhh.middle.jdbc.returnpk;

import com.yuhh.middle.jdbc.smis.util.JdbcUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 获取自动生成的主键
 */
public class ReturnGeneratePkTest {
    @Test
    void testStatement() throws Exception {
        String sql = "insert into t_student(name,age) values('小倩',19)";
        Connection conn = JdbcUtil.getConn();
        Statement st = conn.createStatement();
        st.execute(sql, Statement.RETURN_GENERATED_KEYS);//设置返回自动生成的主键
        //====================================================
        ResultSet rs = st.getGeneratedKeys();//获取主键
        if (rs.next()) {
            long pk = rs.getLong(1);
            System.out.println(pk);
        }
        //====================================================
        JdbcUtil.close(conn, st, rs);
    }

    @Test
    void testPrepareStatement() throws Exception {
        String sql = "insert into t_student(name,age) values(?,?);";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//设置返回生成的主键
        ps.setString(1, "宁采臣");
        ps.setInt(2, 23);
        ps.executeUpdate();
        //====================================================
        ResultSet rs = ps.getGeneratedKeys();//获取主键
        if (rs.next()) {
            long pk = rs.getLong(1);
            System.out.println(pk);
        }
        //====================================================
        JdbcUtil.close(conn, ps, rs);
    }
}
