#ConcurrentMap -并发映射
##概述
**1.本质上是一个map，依然以剑指队的结构来存储数据
2.ConcurrentMap提供了并发且安全的方式来读写教程**
##ConcurrentHashMap 并发哈希映射
1.底层结构是基于数组+链表存储，默认初始容量16，默认加载因子0.75，每次扩容默认增加一倍

2.ConcurrentHashMap 是一个异步式线程安全映射,在其中引入了分段锁机制来解决效率降低的问题，在后续JDK版本中，
concurrentHashMap在分段锁的基础上引入了`读写锁`来提高效率
####a.读锁： 允许多个线程读，不允许线程写
####b.写锁 ：只允许一个县城写，不允许线程读

3.在JDK1.8中，ConcurrentHashMap引入了`CAS算法(Compare And Swap)`保证异步线程安全，摒弃了锁。
`CAS算法`需要与具体的内核结合，目前几乎所有的内核架构都是`支持CAS`

###### CAS语义：我认为V的值应该是A，如果是，那么将V的值更新为B，否则不修改并告诉V的值实际为多少 
###### V：内存值 A：旧的预期值 B：新的预期值

`HashTable对外提供的方法都是非静态方法--同步非静态方法的锁对象是
this-- this表示当前对象--HashTable是以当前对象作为锁对象`

##ConcurrentNavigableMap