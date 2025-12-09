package com.example.bank;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBank {

    @Test
    void verboseConcurrentTransferTest() throws InterruptedException {

        System.out.println("========== CONCURRENT MONEY TRANSFER TEST ==========\n");

        Bank bank = new Bank();

        // 1) CREATE ACCOUNTS
        Account acc1 = new Account(1, 500);
        Account acc2 = new Account(2, 800);
        Account acc3 = new Account(3, 1200);
        Account acc4 = new Account(4, 1500);

        List<Account> accounts = Arrays.asList(acc1, acc2, acc3, acc4);

        // 2) PRINT INITIAL STATE
        System.out.println("INITIAL BALANCES:");
        accounts.forEach(a -> System.out.println("  com.example.bank.Account " + a.getId() + ": " + a.getBalance() + " USD"));

        int initialTotal = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("\nTOTAL MONEY AT START: " + initialTotal + " USD\n");

        // 3) DEFINE PARALLEL OPERATIONS
        class TransferOperation {
            final Account from;
            final Account to;
            final int amount;

            TransferOperation(Account from, Account to, int amount) {
                this.from = from;
                this.to = to;
                this.amount = amount;
            }
        }

        List<TransferOperation> ops = Arrays.asList(
                new TransferOperation(acc1, acc2, 100),
                new TransferOperation(acc2, acc3, 150),
                new TransferOperation(acc3, acc4, 200),
                new TransferOperation(acc4, acc1, 50),
                new TransferOperation(acc1, acc3, 70),
                new TransferOperation(acc3, acc2, 90),
                new TransferOperation(acc2, acc4, 60),
                new TransferOperation(acc4, acc3, 130)
        );

        System.out.println("TRANSFER PLAN (8 operations):");
        for (TransferOperation op : ops) {
            System.out.println("  " + op.from.getId() + " - " + op.to.getId()
                    + "     amount: " + op.amount + " USD");
        }
        System.out.println();

        // 4) EXECUTE TRANSFERS IN PARALLEL
        ExecutorService pool = Executors.newFixedThreadPool(4);

        System.out.println("=== STARTING TRANSFERS ===\n");

        for (TransferOperation op : ops) {
            pool.submit(() -> {
                System.out.println(
                        "[Thread " + Thread.currentThread().getName() + "] "
                                + "Transferring " + op.amount + " USD "
                                + "from Account " + op.from.getId()
                                + " - Account " + op.to.getId()
                );
                bank.transfer(op.from, op.to, op.amount);

                System.out.println(
                        "[Thread " + Thread.currentThread().getName() + "] "
                                + "AFTER TRANSFER      "
                                + "Balance " + op.from.getId() + ": " + op.from.getBalance()
                                + " | Balance " + op.to.getId() + ": " + op.to.getBalance()
                );
            });
        }

        pool.shutdown();
        pool.awaitTermination(3, TimeUnit.SECONDS);

        // 5) PRINT FINAL STATE
        System.out.println("\n=== FINAL BALANCES ===");
        accounts.forEach(a ->
                System.out.println("Account " + a.getId() + ": " + a.getBalance() + " USD")
        );

        int finalTotal = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("\nTOTAL MONEY AFTER ALL TRANSFERS: " + finalTotal + " USD\n");

        // 6) CHECK MONEY PRESERVATION
        System.out.println("Check: total before == total after");
        System.out.println("  " + initialTotal + " == " + finalTotal);

        assertEquals(initialTotal, finalTotal,
                "Money must not disappear — bank total must remain unchanged!");

        System.out.println("\n========== TEST PASSED SUCCESSFULLY ==========");
    }
}
