package cn.tedu.src.concurrent.blockingQueue.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
//        参数表示信号数量      -- 10  爷的餐厅有10个座位
        Semaphore s = new Semaphore(10);
        for (int i = 0; i <20 ; i++) {
//            System.out.println("现在的桌子数量"+s.availablePermits());

            Thread thread =new Thread(new Table(s));
            thread.start();
        }
    }
}
class Table implements Runnable{
    private Semaphore s ;

    public Table(Semaphore s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
//            获取一个信息号
            s.acquire();
            System.out.println("来了一个崽种，桌子数量-1,现在的桌子数量"+s.availablePermits());
            Thread.sleep((long) (Math.random()*10000));

            if(s.availablePermits()==0){
//                实际工程中，定然有算法计算 这次是纯随机     。。。不管了
//                不想转单位了 ，溜
                System.out.println("现在没有空桌，预计等待时间"+Math.random()*10000);
            }
//            可用信号多了一个
            s.release();
            System.out.println("这个崽种吃完了 桌子数量+1,现在的桌子数量"+s.availablePermits());
//            System.out.println("现在的桌子数量");
//            当信号释放之后，被阻塞的崽种线程就可以抢占信号 来恰饭
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        桌子-1.，当没有桌子了的时候
//        后来的线程需要陷入阻塞
    }
}
