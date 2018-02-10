package com.yuhh.middle.jdbc.tx;

import com.yuhh.middle.jdbc.smis.util.JdbcUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 模拟银行转账需求
 */
public class TransactionTest {

    @Test
    void test() throws Exception {
        Connection conn = JdbcUtil.getConn();
        //查找张无忌的账户，余额是否大于等于1000
        String sql = "select * from t_account where name=? and balance>=?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "张无忌");
        ps.setInt(2, 1000);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new RuntimeException("张无忌的余额不足！");
        }

        //从张无忌的账户上扣除1000元
        sql = "update t_account set balance=balance-? where name=?;";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, 1000);
        ps.setString(2, "张无忌");
        ps.executeUpdate();

        //============================================================
        //模拟停电
        //============================================================
        int a = 1 / 0;

        //赵敏的账户上增加1000元
        sql = "update t_account set balance=balance+? where name=?;";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, 1000);
        ps.setString(2, "赵敏");
        ps.executeUpdate();

        JdbcUtil.close(conn, ps, rs);
    }
    @Test
    void test2() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = JdbcUtil.getConn();
        //查找张无忌的账户，余额是否大于等于1000
        String sql = "select * from t_account where name=? and balance>=?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "张无忌");
            ps.setInt(2, 1000);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new RuntimeException("张无忌的余额不足！");
            }

            //关闭事务的自动提交
            conn.setAutoCommit(false);

            //从张无忌的账户上扣除1000元
            sql = "update t_account set balance=balance-? where name=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1000);
            ps.setString(2, "张无忌");
            ps.executeUpdate();

            //============================================================
            //模拟停电
            //============================================================
            //int a = 1 / 0;

            //赵敏的账户上增加1000元
            sql = "update t_account set balance=balance+? where name=?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1000);
            ps.setString(2, "赵敏");
            ps.executeUpdate();
            //提交事务
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //有异常时进行回滚操作
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }


    }
}
