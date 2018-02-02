package com.yuhh.base.thread;

/***
 * 线程锁（线程同步）
 */

/***
 * 1.同步代码块
 */

class Thread1 implements Runnable {
    private int thicket = 5;

    @Override
    public void run() {
//        for (int i = 0; i < 20; i++) {
//            synchronized (this) {
//                if (this.thicket > 0) {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(Thread.currentThread().getName() + "-->" + this.thicket--);
//                }
//            }
//        }
        sale();
    }

    /***
     * 2.同步方法
     */
    public synchronized void sale() {
        for (int i = 0; i < 20; i++) {
            if (this.thicket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-->" + this.thicket--);
            }
        }
    }
}

public class ThreadLock {
    public static void main(String[] args) {
        Thread1 th1 = new Thread1();
        Thread thread1 = new Thread(th1, "线程1");
        Thread thread2 = new Thread(th1, "线程2");
        Thread thread3 = new Thread(th1, "线程3");
        Thread thread4 = new Thread(th1, "线程4");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
