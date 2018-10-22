package com.zhangshaolin.thread;

/**
 * @program: java-study
 * @description: 银行账户
 * @author: zhangshaolin
 * @create: 2018-10-22 15:37
 **/
public class BankAccount {

    /**
     * 账户初始化有100元
     */
    private int balance = 100;

    /**
     * 获取余额
     *
     * @return
     */
    public int getBalance() {
        return balance;
    }

    /**
     * 取款
     *
     * @param amount
     */
    public void withdraw(int amount) {
        balance = balance - amount;
    }

}
