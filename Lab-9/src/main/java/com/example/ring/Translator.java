package com.example.ring;

public class Translator implements Runnable {

    private final RingBuffer<String> in;
    private final RingBuffer<String> out;
    private final int id;

    public Translator(RingBuffer<String> in, RingBuffer<String> out, int id) {
        this.in = in;
        this.out = out;
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String msg = in.take();
                String translated = "Translator #" + id + " processed: " + msg;
                out.put(translated);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
