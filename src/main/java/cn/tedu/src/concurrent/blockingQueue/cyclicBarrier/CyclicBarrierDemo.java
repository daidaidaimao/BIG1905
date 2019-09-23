package cn.tedu.src.concurrent.blockingQueue.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //            此时运行结果，谁到了起跑线 谁就开始跑了
//            但是需要的效果是 等人到齐一起跑
        CyclicBarrier cb = new CyclicBarrier(7);
        new Thread(new Runner(cb),"张仁飞").start();
        new Thread(new Runner(cb),"李帅").start();
        new Thread(new Runner(cb),"陈朝阳").start();
        new Thread(new Runner(cb),"陆颖杰").start();
        new Thread(new Runner(cb),"茄子").start();
        new Thread(new Runner(cb),"wdnmd").start();
        new Thread(new Runner(cb),"卢本伟").start();
    }

    static class Runner implements  Runnable{
        private CyclicBarrier cb ;

        public Runner(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {


            String name = Thread.currentThread().getName();

            System.out.println(name+"到了起跑线 准备冲");


            try {
                cb.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name+"冲了冲了");
//            改！

        }
    }
}

