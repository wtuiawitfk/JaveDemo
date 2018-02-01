package com.yuhh.demo.annotation;

/**
 * 一个测试类
 */
public class EmployeeDaoTest {
    @MyBefore   //测试前要做的
    public static void init() {
        System.out.println("初始化。。。");
    }
    @MyAfter    //测试后要做的
    public static void destroy() {
        System.out.println("程序退出。。。");
    }
    @MyTest     //要测试运行的方法
    public static void update() {
        System.out.println("update...");
    }
    @MyTest
    public static void delete() {
        System.out.println("delete...");
    }
    @MyTest
    public static void save() {
        System.out.println("save...");
    }

}
