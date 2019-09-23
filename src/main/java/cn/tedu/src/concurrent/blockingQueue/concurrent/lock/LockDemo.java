package cn.tedu.src.concurrent.blockingQueue.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    static int i = 0;
//    private static
    public static void main(String[] args) throws InterruptedException {
//        true 公平策略
//        false 非公平策略
        Lock l = new ReentrantLock(true);
        new Thread(new Add(l)).start();
        new Thread(new Add(l)).start();
        Thread.sleep(2000);
        System.out.println(i);
    }
}

class Add implements Runnable{
    private Lock l;

    Add(Lock l) {
        this.l = l;
    }

    @Override
    public void run() {
//        1.加锁
        l.lock();
        for (int i = 0; i <10000 ; i++) {
            LockDemo.i++;
        }
//        2、解锁
        l.unlock();

    }
}
