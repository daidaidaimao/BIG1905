package cn.tedu.src.compliasjs包.blockingQueue;


import java.util.concurrent.PriorityBlockingQueue;


public class Proprity {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<SS> queue3 = new PriorityBlockingQueue<>(7);
/*        queue3.put("a");
        queue3.put("b");
        queue3.put("c");
        queue3.put("d");
        queue3.put("e");
        queue3.put("f");
        queue3.put("g");
        for (int i=0;i<7;i++){
            System.out.println(queue3.take());
        }*/
        queue3.put(new SS("213",2));
        queue3.put(new SS("213",5));
        queue3.put(new SS("213",3));
        queue3.put(new SS("213",8));
        queue3.put(new SS("213",9));
        queue3.put(new SS("213",11));
        queue3.put(new SS("213",12));
        for (int i=0;i<7;i++){
            System.out.println(queue3.take());
        }

    }

}
