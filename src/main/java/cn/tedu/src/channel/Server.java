package cn.tedu.src.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    public static void main(String[] args) {
        try {
//        开启服务器端通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
//            监听端口
            ssc.bind(new InetSocketAddress(8090));
//            设置为非阻塞
            ssc.configureBlocking(false);
//            接受连接
            SocketChannel sc = ssc.accept();
            while (sc == null){
                sc = ssc.accept();
            }
//            读取数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            sc.read(buffer);
//            解析数据
            buffer.flip();
//            下面效率低
//            while (buffer.hasRemaining()){
//                System.out.println(buffer.get());
//            }
//            这个效率高
            byte[] data = buffer.array();
            System.out.println(new String(data,0,buffer.limit()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
