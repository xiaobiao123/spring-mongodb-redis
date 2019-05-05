###**一、什么是存储引擎**
​	关系数据库表是用于存储和组织信息的数据结构，可以将表理解为由行和列组成的表格，类似于Excel的电子表格的形式。有的表简单，有的表复杂，有的表根本不用来存储任何长期的数据，有的表读取时非常快，但是插入数据时去很差

####1、MyISAM（表锁定）

（1）查看mysql支持存储引擎的命名：show engines;
（2）MyISAM表是独立于操作系统的，这说明可以轻松地将其从Windows服务器移植到Linux服务器。MyISAM引擎表会在本地创建3个文件
tb_demo.frm，存储表定义、tb_demo.MYD，存储数据、tb_demo.MYI，存储索引。
无法处理事务，适应与选择密集型的表：MyISAM存储引擎在筛选大量数据时非常迅速，这是它最突出的优点、插入密集型的表：MyISAM的并发插入特性允许同时选择和插入数据。例如：MyISAM存储引擎很适合管理邮件或Web服务器日志数据

http://blog.csdn.net/xifeijian/article/details/20316775

####2、InnoDB（行锁）
​	InnoDB是一个健壮的事务型存储引擎，还引入了行级锁定和外键约束
​	1.更新密集的表：InnoDB存储引擎特别适合处理多重并发的更新请求。
​	2.事务：InnoDB存储引擎是支持事务的标准MySQL存储引擎。
​	3.自动灾难恢复：与其它存储引擎不同，InnoDB表能够自动从灾难中恢复。
​	4.外键约束：MySQL支持外键的存储引擎只有InnoDB。
​	5.支持自动增加列AUTO_INCREMENT属性。

#### 3、MEMORY
​	使用MySQL Memory存储引擎的出发点是速度。为得到最快的响应时间，采用的逻辑存储介质是系统内存。当mysqld守护进程崩溃时，所有的Memory数据都会丢失要求存储在Memory数据表里的数据使用的是长度不变的格式不能使用BLOB和TEXT这样的长度可变的数据类型，VARCHAR是一种长度可变的类型，但因为它在MySQL内部当做长度固定不变的CHAR类型，所以可以使用
​	适应与：
​	1.目标数据较小，而且被非常频繁地访问。在内存中存放数据，所以会造成内存的使用，可以通过参数max_heap_table_size控制Memory表的大小，设置此参数，就可以限制Memory表的最大大小。
​	2.如果数据是临时的，而且要求必须立即可用，那么就可以存放在内存表中。
​	3.存储在Memory表中的数据如果突然丢失，不会对应用服务产生实质的负面影响。

Memory同时支持散列索引和B树索引。B树索引的优于散列索引的是，可以使用部分查询和通配查询，也可以使用<、>和>=等操作符方便数据挖掘；散列索引进行“相等比较”非常快，但是对“范围比较”的速度就慢多了，因此散列索引值适合使用在=和<>的操作符中，不适合在<或>操作符中，也同样不适合用在order by子句中。

#### 4、MERGE

​	MERGE存储引擎是一组MyISAM表的组合，这些MyISAM表结构必须完全相同，尽管其使用不如其它引擎突出，但是在某些情况下非常有用。说白了，Merge表就是几个相同MyISAM表的聚合器；Merge表中并没有数据，对Merge类型的表可以进行查询、更新、删除操作，这些操作实际上是对内部的MyISAM表进行操作。Merge存储引擎的使用场景。

​	对于服务器日志这种信息，一般常用的存储策略是将数据分成很多表，每个名称与特定的时间端相关。例如：可以用12个相同的表来存储服务器日志数据，每个表用对应各个月份的名字来命名。当有必要基于所有12个日志表的数据来生成报表，这意味着需要编写并更新多表查询，以反映这些表中的信息。与其编写这些可能出现错误的查询，不如将这些表合并起来使用一条查询，之后再删除Merge表，而不影响原来的数据，删除Merge表只是删除Merge表的定义，对内部的表没有任何影响。

#### 5、**ARCHIVE**

Archive是归档的意思，在归档之后很多的高级功能就不再支持了，仅仅支持最基本的插入和查询两种功能。在MySQL 5.5版以前，Archive是不支持索引，但是在MySQL 5.5以后的版本中就开始支持索引了。Archive拥有很好的压缩机制，它使用zlib压缩库，在记录被请求时会实时压缩，所以它经常被用来当做仓库使用。

### [二、Mysql主键索引、唯一索引、普通索引、全文索引、组合索引的区别](http://www.cnblogs.com/lonelyxmas/p/4594624.html)
#### 1、Mysql索引主要有两种结构：B+树和hash.
hash（散列索引）:hsah索引在mysql比较少用,他以把数据的索引以hash形式组织起来,因此当查找某一条记录的时候,速度非常快.当时因为是hash结构,每个键只对应一个值,而且是散列的方式分布.所以他并不支持范围查找和排序等功能.

B+树:b+tree是mysql使用最频繁的一个索引数据结构,数据结构以平衡树的形式来组织,因为是树型结构,所以更适合用来处理排序,范围查找等功能.相对hash索引,B+树在查找单条记录的速度虽然比不上hash索引,但是因为更适合排序等操作,所以他更受用户的欢迎.毕竟不可能只对数据库进行单条记录的操作. 

#### 2、MySql常见的索引
主键索引、唯一索引、普通索引、全文索引、组合索引
PRIMARY KEY（主键索引）  ALTER TABLE `table_name` ADD PRIMARY KEY ( `column` ) 
UNIQUE(唯一索引)     ALTER TABLE `table_name` ADD UNIQUE (`column`)
INDEX(普通索引)     ALTER TABLE `table_name` ADD INDEX index_name ( `column` )
FULLTEXT(全文索引)      ALTER TABLE `table_name` ADD FULLTEXT ( `column` )
组合索引   ALTER TABLE `table_name` ADD INDEX index_name ( `column1`, `column2`, `column3` ) 

####3、Mysql各种索引区别：

普通索引：最基本的索引，没有任何限制
唯一索引：与"普通索引"类似，不同的就是：索引列的值必须唯一，但允许有空值。
主键索引：它 是一种特殊的唯一索引，不允许有空值。 
全文索引：仅可用于 MyISAM 表，针对较大的数据，生成全文索引很耗时好空间。
组合索引：为了更多的提高mysql效率可建立组合索引，遵循”最左前缀“原则。

####4、索引优点

1.大大加快数据的检索速度;
2.创建唯一性索引，保证数据库表中每一行数据的唯一性;
3.加速表和表之间的连接;
4.在使用分组和排序子句进行数据检索时，可以显著减少查询中分组和排序的时间。

####5、 索引缺点

1.索引需要占物理空间。
2.当对表中的数据进行增加、删除和修改的时候，索引也要动态的维护，降低了数据的维护速度。
在创建索引之前，您必须确定要使用哪些列以及要创建的索引类型。

### 三、MySql分表分区，以及分表后如果想按条件分页查询怎么办

​	日常开发中我们经常会遇到大表的情况，所谓的大表是指存储了百万级乃至千万级条记录的表。这样的表过于庞大，导致[数据库](https://www.2cto.com/database/)在查询和插入的时候耗时太长，性能低下，如果涉及联合查询的情况，性能会更加糟糕。*分表和表分区的目的就是减少数据库的负担，提高数据库的效率，通常点来讲就是提高表的增删改查效率。*

**分表**

​	分表是将一个大表按照一定的规则分解成多张具有独立存储空间的实体表，我们可以称为子表，每个表都对应三个文件，MYD数据文件，.MYI索引文件，.frm表结构文件。这些子表可以分布在同一块磁盘上，也可以在不同的机器上。app读写的时候根据事先定义好的规则得到对应的子表名，然后去操作它。

**分区**

分区和分表相似，都是按照规则分解表。不同在于分表将大表分解为若干个独立的实体表，而分区是将数据分段划分在多个位置存放，可以是同一块磁盘也可以在不同的机器。分区后，表面上还是一张表，但数据散列到多个位置了。app读写的时候操作的还是大表名字，db自动去组织分区的数据。

**mysql分表和分区有什么联系**

1.都能提高mysql的性高，在高并发状态下都有一个良好的表现。
2.分表和分区不矛盾，可以相互配合的，对于那些大访问量，并且表数据比较多的表，我们可以采取分表和分区结合的方式（如果merge这种分表方式，不能和分区配合的话，可以用其他的分表试），访问量不大，但是表数据很多的表，我们可以采取分区的方式等。
3.分表技术是比较麻烦的，需要手动去创建子表，app服务端读写时候需要计算子表名。采用merge好一些，但也要创建子表和配置子表间的union关系。
4.表分区相对于分表，操作方便，不需要创建子表。

分表：一张表分成多个

分区：

垂直分

水平分

#### **分表的几种方式：**

#####1、mysql集群

它并不是分表，但起到了和分表相同的作用。集群可分担数据库的操作次数，将任务分担到多台数据库上。集群可以读写分离，减少读写压力。从而提升数据库性能。

##### **2、自定义规则分表**

```
Range（范围）–这种模式允许将数据划分不同范围。例如可以将一个表通过年份划分成若干个分区。
Hash（哈希）–这中模式允许通过对表的一个或多个列的Hash Key进行计算，最后通过这个Hash码不同数值对应的数据区域进行分区。例如可以建立一个对表主键进行分区的表。
Key（键值）-上面Hash模式的一种延伸，这里的Hash Key是MySQL系统产生的。
List（预定义列表）–这种模式允许系统通过预定义的列表的值来对数据进行分割。
Composite（复合模式） –以上模式的组合使用　

RANGE分区：基于属于一个给定连续区间的列值，把多行分配给分区。
LIST分区：类似于按RANGE分区，区别在于LIST分区是基于列值匹配一个离散值集合中的某个值来进行选择。
HASH分区：基于用户定义的表达式的返回值来进行选择的分区，该表达式使用将要插入到表中的这些行的列值进行计算。这个函数可以包含MySQL 中有效的、产生非负整数值的任何表达式。
KEY分区：类似于按HASH分区，区别在于KEY分区只支持计算一列或多列，且MySQL服务器提供其自身的哈希函数。必须有一列或多列包含整数值。
```

#### 分表后的分页查询

```
1.如果只是为了分页，可以考虑这种分表，就是表的id是范围性的，且id是连续的，比如第一张表id是1到10万，第二张是10万到20万，这样分页应该没什么问题。

2.如果是其他的分表方式，建议用sphinx[sfɪŋks]先建索引，然后查询分页，我们公司现在就是这样干的
```

###四、分表之后想让一个id多个表是自增的，效率实现

```
1、通过MySQL表生成ID
2、通过redis生成ID
3、队列方式
http://www.ttlsa.com/mysql/mysql-table-to-solve-the-increment-id-scheme/
```

### 五、MySql的主从实时备份同步的配置，以及原理(从库读主库的binlog)，读写分离

###六、索引的数据结构，B+树

###七、事务的四个特性，以及各自的特点（原子、隔离）等等，项目怎么解决这些问题

```
事务具有四个特征：原子性（ Atomicity ）、一致性（ Consistency ）、隔离性（ Isolation ）和持续性（ Durability ）。这四个特性简称为 ACID 特性。 

1 、原子性 
事务是数据库的逻辑工作单位，事务中包含的各操作要么都做，要么都不做 

2 、一致性 
事 务执行的结果必须是使数据库从一个一致性状态变到另一个一致性状态。因此当数据库只包含成功事务提交的结果时，就说数据库处于一致性状态。如果数据库系统 运行中发生故障，有些事务尚未完成就被迫中断，这些未完成事务对数据库所做的修改有一部分已写入物理数据库，这时数据库就处于一种不正确的状态，或者说是 不一致的状态。 

3 、隔离性 
一个事务的执行不能其它事务干扰。即一个事务内部的操作及使用的数据对其它并发事务是隔离的，并发执行的各个事务之间不能互相干扰。 

4 、持续性 
也称永久性，指一个事务一旦提交，它对数据库中的数据的改变就应该是永久性的。接下来的其它操作或故障不应该对其执行结果有任何影响。 
```

###八、数据库的锁：行锁，表锁；乐观锁，悲观锁

###九、数据库事务的几种粒度；

####1、一般分为：行锁、表锁、库锁

（1）行锁：访问数据库的时候，锁定整个行数据，防止并发错误。
（2）表锁：访问数据库的时候，锁定整个表数据，防止并发错误。
（3）行锁 和 表锁 的区别：
- 表锁： 开销小，加锁快；不会出现死锁；锁定力度大，发生锁冲突概率高，并发度最低
- 行锁： 开销大，加锁慢；会出现死锁；锁定粒度小，发生锁冲突的概率低，并发度高
####2、 悲观锁 和 乐观锁
  （1）悲观锁：**顾名思义，就是很悲观，**每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会block直到它拿到锁。传统的关系型数据库里边就用到了很多这种锁机制，比如行锁，表锁等，读锁，写锁等，都是在做操作之前先上锁。

  （2）乐观锁：**顾名思义，就是很乐观，**每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据，可以使用版本号等机制。乐观锁适用于多读的应用类型，这样可以提高吞吐量，像数据库如果提供类似于write_condition机制的其实都是提供的乐观锁。

  （3）悲观锁 和 乐观锁的区别：  两种锁各有优缺点，不可认为一种好于另一种，像**乐观锁适用于写比较少的情况下**，即冲突真的很少发生的时候，这样可以省去了锁的开销，加大了系统的整个吞吐量。但**如果经常产生冲突**，上层应用会不断的进行retry，这样反倒是降低了性能，**所以这种情况下用悲观锁就比较合适**。

### 十、关系型和非关系型数据库区别



MySQL  配置优化
```
[mysqld]
port = 3306
serverid = 1
socket = /tmp/mysql.sock
skip-locking

#避免MySQL的外部锁定，减少出错几率增强稳定性。
skip-name-resolve
#禁止MySQL对外部连接进行DNS解析，使用这一选项可以消除MySQL进行DNS解析的时间。但需要注意，如果开启该选项，则所有远程主机连接授权都要使用IP地址方式，否则MySQL将无法正常处理连接请求！
back_log = 384
#back_log 参数的值指出在MySQL暂时停止响应新请求之前的短时间内多少个请求可以被存在堆栈中。如果系统在一个短时间内有很多连接，则需要增大该参数的值，该参数值指定到来的TCP/IP连接的侦听队列的大小。不同的操作系统在这个队列大小上有它自 己的限制。 试图设定back_log高于你的操作系统的限制将是无效的。默认值为50。对于Linux系统推荐设置为小于512的整数。
key_buffer_size = 256M
#key_buffer_size指定用于索引的缓冲区大小，增加它可得到更好的索引处理性能。对于内存在4GB左右的服务器该参数可设置为256M或384M。注意：该参数值设置的过大反而会是服务器整体效率降低！
max_allowed_packet = 4M
thread_stack = 256K
table_cache = 128K
sort_buffer_size = 6M
#查询排序时所能使用的缓冲区大小。注意：该参数对应的分配内存是每连接独占，如果有100个连接，那么实际分配的总共排序缓冲区大小为100 × 6 ＝ 600MB。所以，对于内存在4GB左右的服务器推荐设置为6-8M。
read_buffer_size = 4M
#读查询操作所能使用的缓冲区大小。和sort_buffer_size一样，该参数对应的分配内存也是每连接独享。
join_buffer_size = 8M
#联合查询操作所能使用的缓冲区大小，和sort_buffer_size一样，该参数对应的分配内存也是每连接独享。
myisam_sort_buffer_size = 64M
table_cache = 512
thread_cache_size = 64
query_cache_size = 64M
# 指定MySQL查询缓冲区的大小。可以通过在MySQL控制台观察，如果Qcache_lowmem_prunes的值非常大，则表明经常出现缓冲不够的 情况；如果Qcache_hits的值非常大，则表明查询缓冲使用非常频繁，如果该值较小反而会影响效率，那么可以考虑不用查询缓 冲；Qcache_free_blocks，如果该值非常大，则表明缓冲区中碎片很多。
tmp_table_size = 256M
max_connections = 768
#指定MySQL允许的最大连接进程数。如果在访问论坛时经常出现Too Many Connections的错误提 示，则需要增大该参数值。
max_connect_errors = 10000000
wait_timeout = 10
#指定一个请求的最大连接时间，对于4GB左右内存的服务器可以设置为5-10。
thread_concurrency = 8
#该参数取值为服务器逻辑CPU数量*2，在本例中，服务器有2颗物理CPU，而每颗物理CPU又支持H.T超线程，所以实际取值为4*2=8
skip-networking
#开启该选项可以彻底关闭MySQL的TCP/IP连接方式，如果WEB服务器是以远程连接的方式访问MySQL数据库服务器则不要开启该选项！否则将无法正常连接！
table_cache=1024
#物理内存越大,设置就越大.默认为2402,调到512-1024最佳
innodb_additional_mem_pool_size=4M
#默认为2M
innodb_flush_log_at_trx_commit=1
#设置为0就是等到innodb_log_buffer_size列队满后再统一储存,默认为1
innodb_log_buffer_size=2M
#默认为1M
innodb_thread_concurrency=8
#你的服务器CPU有几个就设置为几,建议用默认一般为8
key_buffer_size=256M
#默认为218，调到128最佳
tmp_table_size=64M
#默认为16M，调到64-256最挂
read_buffer_size=4M
#默认为64K
read_rnd_buffer_size=16M
#默认为256K
sort_buffer_size=32M
#默认为256K
thread_cache_size=120
#默认为60
query_cache_size=32M


下面对几个重要的参数进行详细介绍：

key_buffer_size：表示索引缓存的大小。这个值越大，使用索引进行查询的速度就越快

table_cache：表示同时打开的表的个数。这个值越大，能同时打开的表的个数就越多。这个值不是越大越好，因为同时打开的表过多会影响操作系统的性能。

query_cache_size：表示查询缓冲区的大小。使用查询缓存区可以提高查询的速度。这个方式只使用与修改操作少且经常执行相同的查询操作的情况；默认值是0.

Query_cache_type：表示查询缓存区的开启状态。0表示关闭，1表示开启。

Max_connections：表示数据库的最大连接数。这个连接数不是越大越好，因为连接会浪费内存的资源。

Sort_buffer_size：排序缓存区的大小，这个值越大，排序就越快。

Innodb_buffer_pool_size：表示InnoDB类型的表和索引的最大缓存。这个值越大，查询的速度就会越快。这个值太大了就会影响操作系统的性能。

分页优化

MySQL的limit工作原理就是先读取n条记录，然后抛弃前n条，读m条想要的，所以n越大，性能会越差。

一般的分页做法，测试耗时 10.961s

SELECT * FROM v_history_data  LIMIT 5000000, 10
1
优化后，测试耗时 1.943s

SELECT * FROM v_history_data INNER JOIN (SELECT fid FROM t_history_data LIMIT 5000000, 10) a USING (fid)
1
优化前的SQL需要更多I/O浪费，因为先读索引，再读数据，然后抛弃无需的行。而优化后的SQL(子查询那条)只读索引(Cover index)就可以了，然后通过member_id读取需要的列。

那么如果我们也要查询所有列，有两种方法，一种是id>=的形式，另一种就是利用join，看下实际情况：
SELECT * FROM product WHERE ID > =(select id from product limit 866613, 1) limit 20
查询时间为0.2秒，简直是一个质的飞跃啊，哈哈

另一种写法
SELECT * FROM product a JOIN (select id from product limit 866613, 20) b ON a.ID = b.id
```


