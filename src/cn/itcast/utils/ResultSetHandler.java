package cn.itcast.utils;

import java.sql.ResultSet;

/**
 * Created by yvettee on 2017/10/9.
 */
public interface ResultSetHandler {
    public Object handler(ResultSet rs);
}
