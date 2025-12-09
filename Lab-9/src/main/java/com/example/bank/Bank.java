package com.example.bank;

class Bank {

    public void transfer(Account from, Account to, int amount) {
        Account first = from.getId() < to.getId() ? from : to;
        Account second = from.getId() < to.getId() ? to : from;

        synchronized (first) {
            synchronized (second) {

                if (from.withdraw(amount)) {
                    to.deposit(amount);
                }
            }
        }
    }
}
