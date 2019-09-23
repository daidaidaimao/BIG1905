package cn.tedu.src.concurrent.blockingQueue.atomicDemo;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) {
        new Thread(new Add()).start();
        new Thread(new Add()).start();
        new Thread(new Add()).start();
        new Thread(new Add()).start();
    }
    static class Add implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i <100000 ; i++) {
//                相当于做了个自增  i++
                int i1 = AtomicDemo.ai.incrementAndGet();
                System.out.println(i1);
            }
        }
    }
}
