package cn.tedu.src.concurrent.blockingQueue.ExecutorService.pool;

import java.util.concurrent.*;

public class ExecutorServiceDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        缓存线程池
//        特点
//        没有核心线程 全都是临时线程
//        单台服务器处理的线程数量是有限的
//        导致可以认为这个线程池能够处理无限多的请求
//        临时线程的存活时间是1分钟
//        工作队列是一个同步队列
//        大池子小队列
//        使用场景 适用于短任务 高并发场景
        ExecutorService es = Executors.newCachedThreadPool();


//        特点
//        没有临时线程
//        都是核心线程
//        工作队列是一个linked队列 创建时不指定容量  容量无穷大
//        小锤子大队列
//        适用于长任务
//        例如    百度网盘
//        长任务并发  直播 --限流
        ExecutorService es2 = Executors.newFixedThreadPool(5);
//        既可以执行runnerable 也可以执行 callable
        Future<String> future = es2.submit(new CallableDemo());
        es2.shutdown();
//        从future中将实际结果解析出来
        System.out.println(future.get());

    }
//    泛型规定的是返回结果的类型
static class CallableDemo implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "success";
    }
}
}
