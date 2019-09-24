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
        a.先比较最大事务id 谁大谁赢
        b.若最大事务id一致，则比较myid，谁大谁赢
    3.如果一个节点经过层层比较，多轮竞争，如果能胜过一半及以上节点的时候，这个节点就会成为leader，过半性
    4.在zookeeper集群中，一但选举出leader那么后续添加的节点的事务id和myid无论是多少，只能成为follower
    5.在zookeeper集群中，当leader宕机之后，集群中会选举出新的leader
    6.在zookeeper集群中，因为分裂而产生多个leader的情况称之为脑裂     直接重启 ？确实可以保证只有一个leader
    但是要实时监控，不太行， 如何避免脑裂~ 
    7.脑裂产生条件：
        a.集群分裂
        b.分裂的集群又进行了选举
        c.在zookeeper自己群中，如果存货的个数不足一半的时候，集群不对外提供服务！过半性的另一个体现
    8.zookeeper集群中，节点个数一般是奇数个。容易满足过半性
    9.，zookeeper集群中，会给每一次选举出来的leader 分配一个全局递增的编号epochid，leader会把
    epochid分发给所有follower，假如真的出现了脑裂产生了2个leader，含有leader的部分，分裂出去，剩余部分
    超过一半 产生选举，所以产生了2个leader，老的epochid必然小于新的epochid，此时集群可以确定新旧，去自动
    重启epochid小的leader，
    10.节点状态
        a.looking/voting    选举状态
        b.follower          追随者
        c.leader            领导者
        d.observer          观察者 
    server.x=IP:2888:3888
                election port  2888     

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


#ZAB协议
    一、概述
    1.专门为zookeeper色剂的一套用于原子广播、崩溃恢复的协议    
    如果某个节点进日志失败 但是又要请求  会再给leader发送信息 请求操作 然后在接受到操作之后 重新记录日志 重新执行
    原子广播：保证数据的一致性 
    崩溃恢复：当集群中的leader产生丢失的时候，在满足的条件的前提下，集群会自动选举出新的leader ，
    解决了单点故障问题。
    在崩溃恢复的过程中，新选举出来的leader会分配一个新的epochid
    
    如果某个节点重新启动，那么启动之后没这个节点会自动找当前节点的最大事务id 会再发消息给leader比较事务id是否
    一致，如果事务id一致，这说明数据是一致的，如果不一致，则leader会将差出的操作放入队列中发送消息给该节点，
    让该节点恢复到和当前集群一致，在节点恢复数据的时候，该节点不对外服务
                                                                       
#观察者
    如果一个节点一旦被定义为观察者，那么这个节点的状态不会发生任何改变
    观察者不参与选举，         也不参与投票，但是要监听选举和投票结果根据结果执行对应的指令。
    如果接受写操作observer是没有权利决定这个操作是否执行 而是根据leader的指令来决定
    leader的指令由原子广播过半决定，而observer不参与原子广播
    在实际生产环境中，我们会将绝大部分的节点都设置为observer 观察者， 1000个节点900个observer   
    集群中25个节点1个leader6个follower 18个observer的前提下，有15个observer宕机，集群依然对外服务
    因为observer没有投票权，所以observer是否宕机并不能影响leader的决定
          
    如何设置observer
    设置conf目录下zoo.cfg
    在server上新添一行配置
    peerType=observer  //表示开启观察者模式
    server.x:ip:port:port:observer
    
    
#集群操作

    nc - netcat -底层会自动发送TCP请求
    
#Zookeeper特性和配置
    特性：
    1.过半性   ：   选举过半、存活过半、操作过半（原子光波）
    2.数据一致性
    3.实时性   ：   对节点进行实时监控
    4.顺序性   ：   按照什么顺序接受请求就按什么顺序来执行操作
    5.可靠性   ：   持久化机制（数据写在磁盘上） 崩溃恢复
    6.原子性   ：   一个请求要么都执行要么都不执行
    
    配置信息
    clientPort  客户端端口号
    dataDir  
    dataLogDir
    tickTime    时间单元，默认2000毫秒（2s）凡是涉及到时间的计算，都是以这个为基础
    initLimit   初始化限制   节点重启补充数据时间，
    syncLimit   同步限制    当leader收到请求发给所有follower，等follower发送消息，等syncLimit的时间 默认等10s
    minSessionTimeout   对话超时时间
    maxSessionTimeout   4000~40000
    globalOutstangdingLimit  最大请求堆积树 默认请求1000个
    
    
    
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         