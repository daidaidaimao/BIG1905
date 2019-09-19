package cn.tedu.src.buff;


import java.nio.ByteBuffer;

public class Bytebuff {
    public static void main(String[] args) {
//        字节数组
//        每个位置上都是0
//        0 0 0 0 0 0 0 0 0 0 0 0 0
//        1.capacity
//        容量位 标记当前缓冲区容量 指向最后一位
//        2.position
//        操作位 类似数组中的下标 指向第0位
//        每赋值一位，position向后移一位
//        3.limit
//        限制位
//        4.mark
//        标记位
//        用于进行标记，避免数据大批量产生错误,默认是不启用标记位，默认值-1

        ByteBuffer buffer = ByteBuffer.allocate(10);
//        现在position在第三位 是0
        buffer.put("abc".getBytes());
        buffer.put("ncd".getBytes());
        buffer.put("sdf".getBytes());
//        buffer.put("tyu".getBytes());
//        要获取 就要挪动
//        把position 挪到0位
        buffer.position(0);
//        获取数据 表示获取一个字节
        byte b =buffer.get();
//        获取缓存区数据
        System.out.println("==============获取缓存区数据============");
        System.out.println(b);
        System.out.println(buffer.get());

//        遍历缓冲区
//        1.先将limit挪到position的位置
//        2.将position挪到0位
//        3.开始遍历

//        无参数的position代表获取当前缓冲区的position

//        buffer.limit(buffer.position());
//        buffer.position(0);

//        缓存区操作
//        1.翻转缓存区
//        limit = position
//        position = 0
//        mark = -1
        buffer.flip();
//      等于上面2步

//        2.清空缓存区
//        position = 0
//        limit = capacity
//        mark = -1
//        buffer.clear();

//        3.重置缓存区
//        判断Mark是否小于0 ，如果不小于0，就将position挪到mark
//        buffer.reset();

//        4.重绕缓冲区
//        position = 0
//        mark = -1
//        buffer.rewind();


//        遍历缓存区
        System.out.println("=============遍历=============");
//        方法1.
//        while(buffer.position()<buffer.limit()){
//        方法2
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }
}
