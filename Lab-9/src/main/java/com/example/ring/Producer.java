package com.example.ring;

public class Producer implements Runnable {

    private final RingBuffer<String> buffer;
    private final int id;

    public Producer(RingBuffer<String> buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    @Override
    public void run() {
        int counter = 1;

        while (true) {
            String msg = "Producer #" + id + " generated message " + counter++;
            try {
                buffer.put(msg);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
