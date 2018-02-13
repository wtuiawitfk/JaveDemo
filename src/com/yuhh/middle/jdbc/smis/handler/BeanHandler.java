package com.yuhh.middle.jdbc.smis.handler;

import com.yuhh.middle.jdbc.smis.dao.IResultSetHandler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

/**
 * 数据库查询单个结果的处理类
 * @param <T>
 */
public class BeanHandler<T> implements IResultSetHandler<T>{
    private Class<T> classType;
    //用户自己传入需要处理对象的类的类型
    public BeanHandler(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public T handle(ResultSet rs) throws Exception {
        //只有一个结果
        if (rs.next()) {
            T obj = classType.newInstance();    //只有一个结果
            //使用内省得到用户传入类的BeanInfo
            BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd :
                    pds) {
                String name = pd.getName(); //拿到列名
                Object value = rs.getObject(name);   //拿到值
                //把名字和值写到对象里
                pd.getWriteMethod().invoke(obj, value);
            }
            return obj;
        }
        return null;
    }
}
