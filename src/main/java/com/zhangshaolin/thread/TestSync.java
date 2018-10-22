package com.zhangshaolin.thread;

/**
 * @program: java-study
 * @description: 测试丢失更新操作
 * @author: zhangshaolin
 * @create: 2018-10-22 16:42
 **/
public class TestSync implements Runnable {

    /**
     * 共享的账户值(多线程并发更新)
     */
    private int balance;

    @Override
    public void run() {
        //每个线程都把账户递增50次
        for (int i = 0; i < 50; i++) {
            increment();
            System.out.println("balance is " + balance);
        }
    }

    /**
     * 递增账户值 用的是读取的值 而不是目前的值
     */
    private void increment() {
        int i = balance;
        balance = i + 1;
    }

    public static void main(String[] args) {
        TestSync job = new TestSync();
        Thread one = new Thread(job);
        Thread two = new Thread(job);
        one.start();
        two.start();
    }
}
