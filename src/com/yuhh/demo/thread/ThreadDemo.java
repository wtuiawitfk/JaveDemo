package com.yuhh.demo.thread;

/***
 * 多线程的两种实现方式
 */

/***
 *1.继承Thread类
 */

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->" + "正在运行");
    }
}

/***
 * 实现Runable接口
 */

class MyThread1 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "-->" + "正在运行");
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.setName("MyThread线程");
        mt.start();

        MyThread1 mt1 = new MyThread1();
        Thread rt = new Thread(mt1, "Runable线程");
        rt.start();
    }
}
