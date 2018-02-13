package com.yuhh.middle.jdbc.smis.handler;

import com.yuhh.middle.jdbc.smis.dao.IResultSetHandler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements IResultSetHandler {
    private Class<T> classType;

    public BeanListHandler(Class<T> classType) {
        this.classType = classType;
    }
    @Override
    public Object handle(ResultSet rs) throws Exception {
        List<T> list = new ArrayList<>();
        //处理多个结果
        while (rs.next()) {
            T obj = classType.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd :
                    pds) {
                String name = pd.getName();
                Object value = rs.getObject(name);
                pd.getWriteMethod().invoke(obj, value);
                list.add(obj);
            }
        }
        return list;
    }
}
