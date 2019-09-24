#大数据
一、简介

*   6V

    * 数据体量大,处理的数据量基本每天至少在TB级别左右
    * 数据的种类和样式多，数据可以划分为结构化、半结构化、非机构化数据
    * 数据量的增长速度越来越快
    * 数据价值密度反而越来越低
    * 数据的真实性
    * 数据的连通性
    * 数据的动态性、可视化
    
#Hadoop

* 概述
    * Hadoop本身是Apache提供的一个开源框架，开源 分布式 可拓展的/可伸缩的 可靠的用于分布式计算的框架
    * 市面上，不同的厂商针对Hadoop提供了不同的商用产品：Cloudera的CDH等，上课讲的是源生Hadoop

* 发展历程
    * 创始人 Doug Cutting 和Mike Caferalla 
    * 2002 考虑存储数据
    * 2003 谷歌<The Google FileSystem>(GFS)阐述了原理，但是Google并没有对外公布该框架的使用 doug 就设计出了
    NDFS
    * 2004 Google又发表了<The Google MapReduce>说怎么离线分布式计算的思想，
    * Nutch0.8版本 将NDFS和MapReduce从其中分离了出来，形成了Hadoop 同时NDFS更名为HDFS
    * 2007年时，Doug 从原公司离职，在同年的11月，Doug入职了Yahoo 
    * 在雅虎期间，doug 又参与设计实现了另外一些大数据的框架HBASE Pig
    * 雅虎将 Hadoop hbase pig 等框架贡献给了Apache    
* 版本
    * Hadoop1.0     只包含common   HDFS MapReduce
    * Hadoop2.0
        * 包含Common HDFS MapReduce Yarn 和Hadoop1.0不兼容
    * Hadoop3.0
        * 包含Common HDFS MapReduce Yarn 在3.0的高版本中，在3.0的高版本中还包含了Ozone
* 模块
    * Common    Hadoop的基本模块
    * HDFS      用于分布式存储
    * yarn      用于完成任务调度和资源管理
    * MapReduce 基于yarn完成分布式计算
    * Ozone     对象存储
    * Submarine Hadoop的机器学习的引擎 2019.3
    
* 安装
    * 改主机名
        * 
    * 配置节点免密互通
        * `ssh-keygen`
        * `ssh-copy-id root@hadoop01`
    * 下载解压
    * 修改配置文件
        * `hadoop/etc/hadoop'`
        * `vim hadoop-env.sh`
            * 25行
            * `export JAVA_HOME=/home/presoftware/jdk1.8`
            * 33
            * `export HADOOP_CONF_DIR=/home/software/hadoop-2.7.1/etc/hadoop`
            * `source hadoop-env.sh`
       * `vim core-site.xml`
            * 在configuration里配置


     <property>
            <name>fs.defaultFS</name>
            <value>hdfs://izbp1grkell15a8b3053b3z:9000</value>
    </property>
    <property>
            <name>hadpoop.tmp.dir</name>
            <value>/home/software/hadoop-2.7.1/tmp</value>
    </property>
    
    vim hdfs-site.xml
    <property>
            <name>dfs.replication</name>
            <value>1</value>
    </property>
    
    cp mapred-site.xml.template mapred-site.xml
    vim mapred-site.xml
    <configuration>
    <property>
                <name>mapreduce.framework.name</name>
                <value>yarn</value>
        </property>
    </configuration>
    
    vim yarn-site.xml 
    <configuration>
    
    <!-- Site specific YARN configuration properties -->
    <property>
                    <name>yarn.resourcemanager.hostname</name>
                    <value>izbp1grkell15a8b3053b3z</value>
            </property>
    <property>
                    <name>yarn.nodemanager.aux-services</name>
                    <value>mapreduce.shuffle</value>
            </property>
    </configuration>
    
    vim slaves
    izbp1grkell15a8b3053b3z
    
    修改环境变量
    
    第一次启动先格式化
    Hadoop namenode -format
    
    
    
    
  