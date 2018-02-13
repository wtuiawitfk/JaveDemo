package com.yuhh.middle.jdbc.smis.dao;

import java.sql.ResultSet;

/**
 * 一个通用的结果集处理接口
 * @param <T>：泛型，处理所有的类
 */
public interface IResultSetHandler<T> {
    T handle(ResultSet rs) throws Exception;
}
