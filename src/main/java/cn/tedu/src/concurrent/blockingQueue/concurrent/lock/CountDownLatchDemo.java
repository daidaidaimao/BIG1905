package cn.tedu.src.concurrent.blockingQueue.concurrent.lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(6);
        new Thread(new Teacher(count)).start();
        new Thread(new Student(count)).start();
        new Thread(new Student(count)).start();
        new Thread(new Student(count)).start();
        new Thread(new Student(count)).start();
        new Thread(new Student(count)).start();
//        在计数归0之前 需要让主函数所在的线程 先陷入阻塞
        count.await();
//        当计数归0自动放开阻塞
        System.out.println("===================开始考试===================");

    }
}

class Teacher implements Runnable{
    private CountDownLatch count;
    public Teacher(CountDownLatch count) {
        this.count = count;
    }

    @Override
    public void run() {
        System.out.println("考官来拉!!!!!!!!!!!!!!");
        count.countDown();
    }
}

class Student implements Runnable{
    private CountDownLatch count;
    public Student(CountDownLatch count) {
        this.count=count;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random()*10000));
            System.out.println("考生来拉~~~~~~~~~~~~~~");
            count.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
