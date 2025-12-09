package com.example.ring;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        RingBuffer<String> buf1 = new RingBuffer<>(10);
        RingBuffer<String> buf2 = new RingBuffer<>(10);

        // Start 5 producer daemon threads
        for (int i = 1; i <= 5; i++) {
            Thread t = new Thread(new Producer(buf1, i));
            t.setDaemon(true);
            t.start();
        }

        // Start 2 translator daemon threads
        for (int i = 1; i <= 2; i++) {
            Thread t = new Thread(new Translator(buf1, buf2, i));
            t.setDaemon(true);
            t.start();
        }

        // Main thread prints 100 messages from second buffer
        for (int i = 1; i <= 100; i++) {
            String msg = buf2.take();
            System.out.println(i + ": " + msg);
        }

        System.out.println("Done.");
    }
}
