package cn.tedu.src.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 多路复用选择器
 * 针对事件进行选择利用selector可以实现1对多的连接效果
 * 面向通道进行选择
 * 要求被选择的通道
 * 必须是非阻塞的
 *
 * */
public class Selector {
    public static void main(String[] args) throws IOException {

//        开启服务器端通道
        ServerSocketChannel ssc = ServerSocketChannel.open();

//        绑定要监听的端口
        ssc.bind(new InetSocketAddress(8090));
//        ssc.configureBlocking(false);
//        非阻塞设置
        ssc.configureBlocking(false);

//        开启选择器
        java.nio.channels.Selector selc = java.nio.channels.Selector.open();

//        将通道注册到选择器
//        让选择器 管理 接受
        ssc.register(selc, SelectionKey.OP_ACCEPT);

//        让服务器一直运行
        while(true){
//            进行选择
            selc.select();

//            获取这次选择出来的是事件类型
            Set<SelectionKey> keys = selc.selectedKeys();

//            根据事件类型处理
//            遍历集合
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
//                                无非是三种事件
//                accept/read/write
                if(key.isAcceptable()){
//                    开始处理
//                    从这个事件中，将通道取出来
                    ServerSocketChannel sscx  = (ServerSocketChannel) key.channel();

//                    接受连接
                    SocketChannel sc = sscx.accept();
                    System.out.println("=========连接成功==========");
                    sc.configureBlocking(false);
//                    注册读事件
                    sc.register(selc,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                }
                if(key.isReadable()){
//                    获取通道
                    SocketChannel sc = (SocketChannel) key.channel();
//                    读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    sc.read(buffer);
//                    解析数据
                    byte[] array = buffer.array();
                    System.out.println(new String(array,0,buffer.position()));
//                    注销掉read事件，防止重复读取
//                    先获取该通道上所有的事件
//                    异或 或者减法
                    sc.register(selc,key.interestOps() ^ SelectionKey.OP_READ);
                }
                if(key.isWritable()){
//                    从事件中获取通道
                    SocketChannel sc = (SocketChannel) key.channel();
//                    写数据
                    sc.write(ByteBuffer.wrap("hello client".getBytes()));
//                    注销掉可写事件   防止重复写
                    sc.register(selc,key.interestOps() - SelectionKey.OP_WRITE);
                }
                iterator.remove();
            }
        }
    }
}
