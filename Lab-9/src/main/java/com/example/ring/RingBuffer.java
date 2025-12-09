package com.example.ring;

public class RingBuffer<T> {

    private final Object[] buffer;
    private int head = 0; // index for reading
    private int tail = 0; // index for writing
    private int count = 0;

    public RingBuffer(int capacity) {
        buffer = new Object[capacity];
    }

    public synchronized void put(T item) throws InterruptedException {
        while (count == buffer.length) {
            wait(); // buffer full
        }

        buffer[tail] = item;
        tail = (tail + 1) % buffer.length;
        count++;

        notifyAll(); // notify consumers
    }

    @SuppressWarnings("unchecked")
    public synchronized T take() throws InterruptedException {
        while (count == 0) {
            wait(); // buffer empty
        }

        T item = (T) buffer[head];
        head = (head + 1) % buffer.length;
        count--;

        notifyAll(); // notify producers

        return item;
    }
}
