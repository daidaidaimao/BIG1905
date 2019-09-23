#ZooKeeper
    一、概述
    1.ZooKeeper是Apache提供的一通用于分布式架构管理的框架
    2.是根据谷歌一篇论文写出来的
    
    二、分布式的问题
    1.在分布式中，为了确定读写操作索要连接的主机，需要引入管理节点
    2.如果管理节点只有一个，存在单点故障，需要引入管理集群
    3.不能让2个管理节点同时对一个节点进行管理，最好是管理节点不同时对外接受请求（？如何确定
    哪个管理节点接受请求） 需要选举出一个主节点 对外服务器 以及对自己的集群进行管理，需要确定
    一套选举算法，
    4.管理集群中还需要存在崩溃恢复机制，主节点挂了 则再选举一个主节点出来，新主节点如何确定接
    收信息， 
    5.所以，主节点之间需要进行信息的同步共享，则新的主节点就不会产生信息的丢失
    
    三、Zookeeper的安装
    1.单机模式  在一台服务器上安装，只能使用原来框架部分或全部功能（大多数为 小部分功能）
    2.伪分布式  在一台服务器上安装，但是利用进程来进行模拟使用，能用使用框架的大部分功能
    3.完全分布式 在集群中安装，能使用框架中的所有功能
    
    四、细节
    1.特点
        a.ZooKeeper 本身是一个树状结构 ，也叫Znode树
        b.ZooKeeper  根节点"/",
        c.ZooKeeper 没一个节点称之为一个Znode 节点，
        d.所有的节点路径必须以"/"为起点进行计算
        e.所有的节点必须携带数据
        f.Znode树维系在内存以及磁盘中，维系在内存中的目的是为了快速操作，维系在磁盘中的目的是为了保证数据不丢失
        g.ZooKeeper 理论上可以作为缓存服务器使用，但是需要注意实际开发中不这么用，因为ZooKeeper进行分布式管理
        和协调的，如果占用大量内存会导致ZooKeeper的管理性能下降
        h.在zookeeper中，会对每一次的写操作（创建/更新/删除）分配一个全局递增的编号，这个称之为事务ID--Zxid
        i.没听清
     
     五、节点类型
     1.临时节点
     2.持久节点     
     3.顺序
     4.非顺序
    
   `|持久节点|临时节点
 ---|:---:|:---:
顺序节点|持久顺序节点|临时顺序节点
非顺序节点|持久非顺序节点|临时非顺序节点

    
    六、集群模式
    1.tar
    2.cd conf/
    3.cp zoo_***.cfg zoo.cfg
    4.vim zoo.cfg
    5.更改路径 到zookeeper主目录下的temp (自己创建)
    6.最底下加上server.*(正整数)=ip:port:port
                server.*=ip:port:port
                ^^^
    7.进去temp目录 vim myid 
    8.当前IP的* 值
    
    七、选举机制
    1.选举细节:
        a.当前节点的最大事务ID
        b.当前节点的选举编号、选举id-myid
        c.逻辑时钟值，控制参加选举的节点都处在同一轮选举上（防止一轮选举没结束 又来第二轮）
    2.比较原则：
        a.

 ```
create /news 'new server'
create /news/news01 ''  
create /music ''
delete /music 
rmr /news
create /news 'wdnmd'
get /news
set /news 'news server'
create -e /redis
create -s /news/n
```
    [zk: localhost:2181(CONNECTED) 10] get /news              
     news server
     cZxid = 0x6                            创建事务ID
     ctime = Sun Sep 22 14:06:56 CST 2019   创建时间
     mZxid = 0x7                            节点修改顺序的事务ID
     mtime = Sun Sep 22 14:08:49 CST 2019   节点修改数据的时间
     pZxid = 0x6                            记录子节点个数变化的事务ID
     cversion = 0                           子节点个数变化次数
     dataVersion = 1                        数据版本 记录当前节点被修改的次数
     aclVersion = 0                         记录节点权限变化次数
     ephemeralOwner = 0x0                   标记当前节点是一个临时节点，持久节点此项必为0，如果是临时节点，此项为sessionId
     dataLength = 11                        数据字节长度
     numChildren = 0                        子节点个数
    

命令|解释
 ---  |---
create /news 'news server'|在根节点下创建一个子节点news
ls /|查看/所有的子节点
delete /music|删除根节点下music节点，要求被删除的节点必须没有子节点
rmr /news|递归删除 
get /news|表示查看news的信息以及节点信息
set /news 'news server'|修改news节点信息
create -e /video|创建临时节点video
create -s /news/n|创建自动编号的顺序节点