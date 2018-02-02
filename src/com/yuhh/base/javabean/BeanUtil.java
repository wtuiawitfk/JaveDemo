package com.yuhh.base.javabean;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * javabean工具类
 */
public class BeanUtil {
    //将一个javabean转换成map
    public static Map<String, Object> bean2map(Object bean) throws Exception {
        Map<String, Object> map = new HashMap<>();

        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        //取得对象里的属性值
        for (PropertyDescriptor pd :
                pds) {
            String propertyName = pd.getName(); //属性名
            Object propertyValue = pd.getReadMethod().invoke(bean); //属性值
            map.put(propertyName, propertyValue);
        }

        return map;

    }

    public static Object map2bean(Map<String, Object> beanMap, Class beanType) throws Exception {
        Object obj = beanType.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(beanType, Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd :
                pds) {
            String propertyName = pd.getName(); //属性名
            Object propertyValue = beanMap.get(propertyName);

            //获取setter方法
            pd.getWriteMethod().invoke(obj, propertyValue);
        }
        return obj;
    }

    public static void main(String[] args) throws Exception {
        Person p = new Person(001,"大海一余",32,true);
        Map<String, Object> map = bean2map(p);
        System.out.println(map);
        Person p2 = (Person) map2bean(map, Person.class);
        System.out.println(p2);
    }
}
