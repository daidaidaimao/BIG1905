package cn.tedu.src.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) {
//        创建一个客户端通道
        try {
//            1.开启一个客户端通道
            SocketChannel sc = SocketChannel.open();
//            手动将通道设置非阻塞
            sc.configureBlocking(false);
//            2.发起连接
            sc.connect(new InetSocketAddress("localhost",8090));

//            因为是非阻塞，所以要建立连接才能写数据 先判断连接是否建立
            while (!sc.isConnected()) {
//                在这个方法中，他会自动计数没如果连接多次失败
//                那么会认为这个连接无法建议
//                抛出异常
                sc.finishConnect();
            }
//            3.写出数据
            sc.write(ByteBuffer.wrap("Hello Server".getBytes()));  //需要一个缓冲区 且加入数据  allocate 不能假数据

//            4.关流
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
