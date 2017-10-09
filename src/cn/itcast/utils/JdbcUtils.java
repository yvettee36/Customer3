package cn.itcast.utils;

import cn.itcast.domain.Customer;

import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    //add update delete
    public static void update(String sql, Object params[]) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils_DBCP.getConnection();
            ps = conn.prepareStatement(sql);
            //Object params[]替换sql里面的参数
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
        } finally {
            JdbcUtils_DBCP.release(conn, ps, rs);
        }
    }

    public static Object query(String sql, Object params[], ResultSetHandler handler) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils_DBCP.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
            return handler.handler(rs);
        } finally {
            JdbcUtils_DBCP.release(conn, ps, rs);
        }
    }

}
