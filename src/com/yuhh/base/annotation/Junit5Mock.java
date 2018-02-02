package com.yuhh.base.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 控制自定义标签（before,after,test）的程序
 * 赋予三种标签功能并保证执行的流程
 *
 * 操作步骤：
 * 1.先找到测试类的字节码：EmployeeDaoTest
 * 2.获取EmployeeDaoTest中所有的公共方法
 * 3.迭代出每一个Method方法
 * 4.边迭代边判断，哪一些方法上使用了@before/@test/@after标签标注
 * beforeList  存储使用了@before标签标注的对象
 * testList    存储使用了@test标签标注的对象
 * afterList   存储使用了@after标签标注的对象
 * 5.控制方法执行的流程
 * 执行beforeList中的方法
 * 迭代出testList中的所有方法对象，并执行
 * 执法afterList中的方法
 */
public class Junit5Mock {
    private static List<Method> beforeList = new ArrayList<>();
    private static List<Method> testList = new ArrayList<>();
    private static List<Method> afterList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
//         * 1.先找到测试类的字节码：EmployeeDaoTest
        Class clazz = EmployeeDaoTest.class;
        Object obj = clazz.newInstance();
//                * 2.获取EmployeeDaoTest中所有的公共方法
        Method[] methods = clazz.getMethods();
//                * 3.迭代出每一个Method方法
//                * 4.边迭代边判断，哪一些方法上使用了@before/@test/@after标签标注
// *          beforeList  存储使用了@before标签标注的对象
// *          testList    存储使用了@test标签标注的对象
// *          afterList   存储使用了@after标签标注的对象
        for (Method method :
                methods) {
            if (method.isAnnotationPresent(MyBefore.class)) {
                beforeList.add(method);
            } else if (method.isAnnotationPresent(MyTest.class)) {
                testList.add(method);
            } else if (method.isAnnotationPresent(MyAfter.class)) {
                afterList.add(method);
            }
        }
// * 5.控制方法执行的流程
//                *      执行beforeList中的方法

//                *      迭代出testList中的所有方法对象，并执行
//                *      执法afterList中的方法
        for (Method testMethod :
                testList) {
            for (Method beforeMethod :
                    beforeList) {
                beforeMethod.invoke(obj);
            }
            testMethod.invoke(obj);
            for (Method afterMethod :
                    afterList) {
                afterMethod.invoke(obj);
            }
        }
    }
}
