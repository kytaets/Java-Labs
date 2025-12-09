package com.example.bank;

public class Account {
    private final int id;
    private int balance;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized boolean withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized int getBalance() {
        return balance;
    }
}
