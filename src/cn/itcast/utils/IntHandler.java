package cn.itcast.utils;

import java.sql.ResultSet;

/**
 * Created by yvettee on 2017/10/9.
 */
public class IntHandler implements ResultSetHandler {
    @Override
    public Object handler(ResultSet rs) {
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
