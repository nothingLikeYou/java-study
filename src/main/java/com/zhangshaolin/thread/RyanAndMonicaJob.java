package com.zhangshaolin.thread;

/**
 * @program: java-study
 * @description: 线程任务(检查余额, 取款) 线程并发测试 操作同一个账户实例
 * @author: zhangshaolin
 * @create: 2018-10-22 15:41
 **/
public class RyanAndMonicaJob implements Runnable {

    /**
     * 共享账户(只有一个)
     */
    private BankAccount mAccount = new BankAccount();

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            makeWithdrawal(10);
            if (mAccount.getBalance() < 0) {
                System.out.println("Overdrawn!");
            }
        }
    }

    /**
     * 检查账户余额,如果透支就列出信息,否则就去睡一会,然后醒来完成提款操作
     *
     * @param amount
     */
    private synchronized void makeWithdrawal(int amount) {
        if (mAccount.getBalance() >= amount) {
            System.out.println(Thread.currentThread().getName() + " is about to withdraw");
            try {
                System.out.println(Thread.currentThread().getName() + " is going to sleep");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " woke up.");
            mAccount.withdraw(amount);
            System.out.println(Thread.currentThread().getName() + " completes the withdrawl");
        } else {
            System.out.println("Sorry, not enough for " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        RyanAndMonicaJob theJob = new RyanAndMonicaJob();
        Thread one = new Thread(theJob);
        Thread two = new Thread(theJob);
        one.setName("Ryan");
        two.setName("Monica");
        one.start();
        two.start();
    }
}
