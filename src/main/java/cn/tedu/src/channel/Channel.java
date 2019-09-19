package cn.tedu.src.channel;


/**
 * 通道
 * 在NIO中，用于进行数据的传输
 * channel可以实现双向传输
 * channel默认是阻塞的，可以手动设置非阻塞
 * 针对不同而场景提供了不同的子类
 * File ：FileChannel
 * UDP：DatagramChannel
 * TCP：SocketChannel、ServerSocketChannel*/
public class Channel {
    public static void main(String[] args) {
        new Client();
        new Server();
    }

}
