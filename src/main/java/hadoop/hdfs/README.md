#HDFS
* 概述
    * 是Hadoop中用于进行分布式存储
    * HDFS 需要一个节点来负责管理，有多个节点负责存储 hdfs结构是一个典型的主从结构
    * 负责管理的节点称之为NameNode 负责存储的节点的名字DataNode
    * 在HDFS中，存储数据的时候会将数据进行切分，切出多个数据快，放到多个节点上
    * HDFS会自动对数据块进行备份，每一个备份被称为副本replication，在HDFS中如果不指定，则默认的副本数量为3
    
* Block 数据块
    * 在HDFS中，数据是以Block形式存储的，即每一个DataNode上会存储多个Block
    * 当文件向HDFS中放入的时候，会自动的切成1个或者多个Block
    * 在Hadoop2.0中，每一个Block的大小默认是128MB
    * 如果1个文件不足一个Block大小，则这个文件整个作为一个Block进行存储，并且这个Block大小跟文件一致
    * HDFS提供了一套类似于Linux的管理系统
    * 在HDFS中，会自动对Block进行编号
    * 切块的意义：
        * 为了能够存储超大文件
        * 为了让Block快速备份
* NameNode
    * NameNode是HDFS中的一个主节点，负责管理DataNode，记录元数据MetaData
    * 元数据主要包含
        * 文件的存储路径
        * 文件大小
        * Block大小
        * 副本数量
        * BlockId
        * Block和DataNode的映射关系
        * 文件的权限
    * NameNode会把元数据存储在内存和磁盘上
    * NameNode将元数据记录在内存中是为了查询快，将元数据记录在磁盘中的目的是为了持久化，是为了数据的恢复
    * 元数据在磁盘上的存储位置由Hadoop.tmp.dir属性来决定
    * 元数据存储在两类文件中
        * edits 记录写操作
        * fsimage 记录元数据 但是需要注意的是，
 * NameNode通过心跳机制来管理DataNode，DataNode定时向NameNode发送心跳信息，要求NameNode去管理DataNode
    * 如果没有收到心跳信息，则NameNode会认为该DataNode 已经lost 丢失。则NameNode会将这个DataNode身上存储的
    Block在其他节点上重新备份保证副本效果
 * 心跳时间是3s，如果超过10min没有收到消息，则认为这个节点丢失
 * DataNode通过RPC的方式给NameNode发心跳