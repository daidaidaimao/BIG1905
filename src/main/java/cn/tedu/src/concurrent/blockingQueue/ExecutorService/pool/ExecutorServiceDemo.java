package cn.tedu.src.concurrent.blockingQueue.ExecutorService.pool;

import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
//        常见一个执行器服务
        ExecutorService service = new ThreadPoolExecutor(
                5, //5个核心线程
                10, //5个核心+5个临时
                5, //存活时间
                TimeUnit.SECONDS, //时间单位
                new ArrayBlockingQueue<Runnable>(5), //工作队列中能够存储5个请求
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("拒绝执行"+ r);
                    }
                }
        );
        for (int i = 0 ;i<18;i++){
        service.execute(new ESRunnable());
        }
        service.shutdown();

    }

    static class ESRunnable implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("hello");
                Thread.sleep(5000);
                System.out.println("finish");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
