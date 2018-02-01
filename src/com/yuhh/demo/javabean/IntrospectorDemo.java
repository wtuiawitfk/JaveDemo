package com.yuhh.demo.javabean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 使用内省机制操作javabean
 */
public class IntrospectorDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);   //通过类的字节码获取javabean
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();   //获取javabean内所有的属性（名称，类型）

        for (PropertyDescriptor pd:
             pds) {
            System.out.println(pd.getName()+"|"+pd.getPropertyType());

            //属性的操作方法
            Method writeMethod = pd.getWriteMethod();   //setter方法
            Method readMethod = pd.getReadMethod();     //getter方法
            System.out.println("setter:" + writeMethod.getName());
            System.out.println("getter:" + readMethod.getName());
            System.out.println("------------------------");
            
        }
    }
}
