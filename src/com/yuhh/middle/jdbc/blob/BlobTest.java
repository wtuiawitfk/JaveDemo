package com.yuhh.middle.jdbc.blob;

import com.yuhh.middle.jdbc.smis.util.JdbcUtil;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 使用大数据类型在数据库中存储图片
 */
public class BlobTest {
    //测试储存
    @Test
    void saveTest() throws Exception {
        String sql = "insert into t_image(content) values(?);";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBlob(1, new FileInputStream("./resources/imac-pro-wallpaper.jpg"));
        ps.executeUpdate();
        JdbcUtil.close(conn, ps);
    }

    //测试读取
    @Test
    void readTest() throws Exception {
        String sql = "select * from t_image where id=1;";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Blob blob = rs.getBlob("content");
            InputStream in = blob.getBinaryStream();
            Files.copy(in, Paths.get("./resources/123.jpg"));//文件拷备
        }
        JdbcUtil.close(conn, ps, rs);
    }
}
