package com.yuhh.middle.jdbc.template;

import com.yuhh.middle.jdbc.smis.dao.IResultSetHandler;
import com.yuhh.middle.jdbc.smis.util.DruidUtil;
import com.yuhh.middle.jdbc.smis.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * jdbc操作模板
 */
public class JdbcTemplate {
    /**
     * jdbc增删改操作模板
     * @param sql：需要执行的SQL语句
     * @param param：SQL语句中的参数
     */
    public static void update(String sql,Object...param){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DruidUtil.getConn();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                ps.setObject(i + 1, param[i]);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn, ps);
        }
    }

    /**
     * 查询操作的通用模板
     * @param sql   sql语句
     * @param rsh   结果处理器
     * @param param 参数
     * @param <T>   泛型
     * @return      查询结果对象
     */
    public static <T> T query(String sql, IResultSetHandler<T> rsh, Object... param) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                ps.setObject(i + 1, param[i]);
            }
            rs = ps.executeQuery();
            T obj = rsh.handle(rs);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }


    private JdbcTemplate() {

    }
}
