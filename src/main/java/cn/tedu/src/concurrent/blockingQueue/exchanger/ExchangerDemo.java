package cn.tedu.src.concurrent.blockingQueue.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(new Productor(exchanger)).start();
        new Thread(new Consumer(exchanger)).start();
    }
    static class Productor implements Runnable{
        private Exchanger<String> exchanger;

        public Productor(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            String info ="AWM";
//            将商品交换给客户
//            同时过程中，商家应该收到客户交换过来的信息
            try {
                String msg = exchanger.exchange(info);
                System.out.println("商家收到了客户给的"+msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class  Consumer implements Runnable{
        private Exchanger<String> exchanger;

        public Consumer(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {

            String money = "4750";
            try {
                String msg = exchanger.exchange(money);
                System.out.println("客户收到了商家的"+msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
