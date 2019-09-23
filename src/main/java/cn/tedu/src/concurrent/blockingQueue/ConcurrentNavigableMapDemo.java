package cn.tedu.src.concurrent.blockingQueue;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentNavigableMapDemo {
    public static void main(String[] args) {
        ConcurrentNavigableMap<String,Integer> map =new ConcurrentSkipListMap<>();

        map.put("1",2);
        map.put("a",3);
        map.put("3",4);
        map.put("d",6);
        map.put("gg",222);
        map.put("ee",12);
        System.out.println(map);


//        截取子映射
//        从头开始截取
        System.out.println(map.headMap("d"));
//        从尾开始截取
        System.out.println(map.tailMap("d"));
//        从指定位置截取到指定位置
        ConcurrentNavigableMap<String, Integer> integerConcurrentNavigableMap = map.subMap("a", "gg");
        System.out.println(integerConcurrentNavigableMap);
    }
}
