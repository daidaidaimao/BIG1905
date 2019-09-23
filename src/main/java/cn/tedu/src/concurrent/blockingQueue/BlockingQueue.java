package cn.tedu.src.concurrent.blockingQueue;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞式队列
 * 本身是一个低劣，满足FIFO特点
 * 所有的阻塞式队列都是有界的，
 * 意味着所有的阻塞式队列的大小都是固定的
 * 如果队列已经满了，新添加的线程会阻塞，直到队列中有元素被取出
 * 如果队列为空，则获取元素的线程阻塞，直到队列中被添加元素
 * 阻塞队列适合于生产消费模型
 * 在阻塞式队列中，要求元素非空
 * 拿到null 一定是空的
 * BlockQueue 是接口 需要实现类
 * 1.ArrayBlockingQueue
 *  a.底层基于数组进行存储
 *  b.使用时需要指定容量，指定后是不可变的。
 * 2.LinkedBlockingQueue
 * 3.PriorityBlockQueue
 * 4.SynchronousQueue
 * */
public class BlockingQueue {
    @Test
    public void demo() throws InterruptedException {
//        1.ArrayBlockingQueue
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
//        2.LinkedBlockingDeque
//        特点：可以不指定容量
//        a.如果指定容量 则 容量固定
//        b.如果不指定容量，则容量默认为integer.MAX.VALUE 即 2的31次方-1  21亿 ，所以可以一般认为，这个队列是无界的。

        LinkedBlockingDeque<String> queue2 = new LinkedBlockingDeque<>();
//        3.PriorityBlockingQueue
//        一个具有优先级的阻塞队列
//        优先级体现在：  priprity 类 拼错了（）不管了
//        使用的时候不指定容量，默认容量为 11 11 11 11 11 11
//        遍历元素的时候是有序的
//        意味着 放入队列中的元素是能够进行排序的
//        问题：如果放入的不是字符串 而是一个普通的类

        PriorityBlockingQueue<String> queue3 = new PriorityBlockingQueue<>(5);

//        4.SynchronousQueue
//        容量只能为1 简单知道就行


//        队列为空
//      1.  remove 抛出异常
//        String remove = queue.remove();
//        System.out.println(remove);

//        2.poll    队列为空返回null
//        String poll = queue.poll();
//        System.out.println(poll);

//        3.take 会阻塞 长期阻塞
//        String take = queue.take();
//        System.out.println(take);

//        4.定时阻塞
//        5s拿不到 就返回null
        String poll = queue.poll(5, TimeUnit.SECONDS);
        System.out.println(poll);
    }

//    ArrayBlockingQueue
public static void main(String[] args) throws InterruptedException {


    ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

//    添加元素
    queue.add("a");
//    queue.offer("b");
//    要抛个异常
//    queue.put("");
    queue.add("b");
    queue.add("c");
    queue.add("d");
    queue.add("e");
//    5个元素已经添满。

//    add方法 再添加就抛出异常
//    queue.add("f");

//    offer方法再添加返回false
//    boolean g = queue.offer("g");
//    System.out.println(g);

//    put方法，会阻塞
//    queue.put("h");

//    定时阻塞，不是长期永久阻塞，塞不进去 就不塞了
//    time 阻塞时间 TimeUnit 时间单位  纳秒 微秒 毫秒 秒 分钟……
    queue.offer("r",5, TimeUnit.SECONDS);

    System.out.println(queue);
}
}
