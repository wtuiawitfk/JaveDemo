package com.yuhh.middle.jdbc.template;

import com.yuhh.middle.jdbc.smis.domain.Column;
import com.yuhh.middle.jdbc.smis.domain.Table;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 把一个对象储存在数据库中的通用模版
 */
public class HibernateTemplate {
    public static void save(Object obj) throws Exception {
        //insert into t_student(name,age) values(?,?);
        StringBuilder sb = new StringBuilder(80);
        sb.append("insert into ");
        //使用注释得到表名
        if (obj.getClass().isAnnotationPresent(Table.class)) {
            String tableName = obj.getClass().getAnnotation(Table.class).value();
            sb.append(tableName);
        }
        sb.append("(");
        System.out.println(sb.toString());
        //得到对象的两个列名和对象的属性值
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        Map<String,Object> beanMap = new HashMap<>();   //将列名和对象属性值存入一个Map集合
        for (PropertyDescriptor pd :
                pds) {
            String name = pd.getName();
            String fieldName = name;
            Field field = obj.getClass().getDeclaredField(name);
            if (field.isAnnotationPresent(Column.class)) {
                Column co = field.getAnnotation(Column.class);
                fieldName = co.value();
                Object value = pd.getReadMethod().invoke(obj);
                beanMap.put(fieldName, value);
            }
        }
        Set<Map.Entry<String, Object>> entries = beanMap.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        //把列名和属性性分别存入两个集合
        List<String> keys = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            keys.add(next.getKey());
            values.add(next.getValue());
        }
        for (int i = 0; i < keys.size(); i++) {
            if (i == keys.size() - 1) {
                sb.append(keys.get(i) + ") ");
                break;
            }
            sb.append(keys.get(i) + ",");
        }
        sb.append("values(");
        for (int i = 0; i < keys.size(); i++) {
            if (i == keys.size() - 1) {
                sb.append("?);");
                break;
            }
            sb.append("?,");
        }
        System.out.println(sb.toString());
        System.out.println(values);
        JdbcTemplate.update(sb.toString(), values.toArray());
    }
}
