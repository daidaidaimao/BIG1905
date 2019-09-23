package cn.tedu.src.concurrent.blockingQueue.ExecutorService.pool;

import java.util.concurrent.*;

public class ForkJoinPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long begin = System.currentTimeMillis();

/*        long num = 0 ;
        for (long i = 0; i <100000000000L ; i++) {
//            num = num + i ;
            num += i;
        }
        System.out.println("num = " + num);*/

//        创建分叉合并池
        ForkJoinPool pool = new ForkJoinPool();
        Future<Long> task = pool.submit(new Sum(1, 110000000000L));
        System.out.println(task.get());
        Long end = System.currentTimeMillis();
        System.out.println(end-begin);
        pool.shutdown();

    }
    static class Sum extends RecursiveTask<Long>{
        private long start;
        private long end;

        public Sum(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start<=10000) {
                long num = 0;
                for (long i = start; i <=end ; i++) {
                    num+=i;

                }
                return num;
            }else {
                long mid = (start+end)/2;
                Sum left = new Sum(start,mid);
                Sum right = new Sum(mid+1,end);

                left.fork();
                right.fork();

                return left.join() | right.join();


            }
//            return null;
        }
    }
}
