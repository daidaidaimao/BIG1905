package cn.tedu.src.concurrent.blockingQueue.ExecutorService.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);

//        延迟5s 再执行提交任务
//        5s + 线程运行时间
//        ses.schedule(new ScheduledRunnable(),5, TimeUnit.SECONDS);

//        每隔多少s执行一次 可以搭配延迟执行
//        ses.scheduleAtFixedRate(new ScheduledRunnable(),0,5,TimeUnit.SECONDS);

//        也是隔5s 但是要加上线程运行的时间  而上面的不需要
        ses.scheduleWithFixedDelay(new ScheduledRunnable(),0,5,TimeUnit.SECONDS);
    }

    static class ScheduledRunnable implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("run!!!!!!!!!!!!!!!");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
