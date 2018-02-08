package com.yuhh.middle.jdbc.smis.util;

import javafx.beans.property.Property;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Set;

public class JdbcUtil {
    private static Properties p = new Properties();

    /**
     * 把驱动连接放在静态代码块内，只运行一次
     */
    static {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            p.load(loader.getResourceAsStream("db.properties"));
        } catch (IOException e) {
            throw new RuntimeException("配置文件有误！", e);
        }
        try {
            Class.forName(p.getProperty("driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("驱动加载失败！", e);
        }
    }

    /**
     * 获取连接对象
     *
     * @return：返回一个连接对象
     */
    public static Connection getConn() {
        try {
            Connection connection = DriverManager.getConnection(p.getProperty("url"),
                    p.getProperty("userName"),
                    p.getProperty("password"));
            return connection;

        } catch (SQLException e) {
            throw new RuntimeException("获取连接失败！", e);
        }
    }

    /**
     * 关闭资源
     */
    public static void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public static void close(Connection conn, Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void close(Connection conn, PreparedStatement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }


    private JdbcUtil() {
    }

}
