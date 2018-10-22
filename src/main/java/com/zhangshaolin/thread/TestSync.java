package com.zhangshaolin.thread;

/**
 * @program: java-study
 * @description: 测试丢失更新操作
 * synchronized:同步锁 可以修饰类,方法
 * 1.修饰方法,使方法变成原子性操作,每次只能被单一的线程执行
 * 2.当前线程在执行时,会加锁,其他线程没有钥匙,必须暂时等待, 执行完之后释放锁,其他线程执行,这样类似于排队机制
 * <p>
 * 死锁:
 * 1.因为两个线程互相持有对方正在等待的东西,没有办法可以脱离这个情况,两个线程卡在那里,必须手动停止其中一个线程
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
    private synchronized void increment() {
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
