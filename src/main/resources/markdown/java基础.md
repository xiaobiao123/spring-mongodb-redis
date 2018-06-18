##java基础

###1、九种基本数据类型的大小，以及他们的封装类。

| 基本类型    | 大小(字节) | 默认值          | 封装类       |
| ------- | ------ | ------------ | --------- |
| byte    | 1      | (byte)0      | Byte      |
| short   | 2      | (short)0     | Short     |
| int     | 4      | 0            | Integer   |
| long    | 8      | 0L           | Long      |
| float   | 4      | 0.0f         | Float     |
| double  | 8      | 0.0d         | Double    |
| boolean | -      | false        | Boolean   |
| char    | 2      | \u0000(null) | Character |
| void    | -      | -            | Void      |

基本类型所占的存储空间是不变的：这种不变性也是java具有可移植性的原因之一。
基本类型是放在栈中，直接存储值。
所有数值类型都有正负号，没有无符号的数值类型。

注：

1.int是基本数据类型，Integer是int的封装类，是引用类型。int默认值是0，而Integer默认值是null，所以Integer能区分出0和null的情况。一旦java看到null，就知道这个引用还没有指向某个对象，再任何引用使用前，必须为其指定一个对象，否则会报错。

2.基本数据类型在声明时系统会自动给它分配空间，而引用类型声明时只是分配了引用空间，必须通过实例化开辟数据空间之后才可以赋值

数组对象也是一个引用对象，将一个数组赋值给另一个数组时只是复制了一个引用，所以通过某一个数组所做的修改在另一个数组中也看的见。



###2、Switch能否用string做参数

###3、equals与==的区别

在JVM中 内存分为栈内存和堆内存。当我们创建一个对象（new Object）时，就会调用它的构造函数来开辟空间，将对象数据存储到堆内存中，与此同时在栈内存中生成对应的引用，当我们在后续代码中调用的时候用的都是栈内存中的引用，还需注意的一点，基本数据类型是存储在栈内存中



1. 使用==比较原生类型如：boolean、int、char等等，使用equals()比较对象。
2. ==返回true如果两个引用指向相同的对象，equals()的返回结果依赖于具体业务实现
3. 字符串的对比使用equals()代替==操作符

以上就是关于equals方法和==操作符的区别，其主要的不同是一个是操作符一个是方法，==用于对比原生类型而equals()方法比较对象的相等性。



###4、 Object有哪些公用方法？

​	1、clone()方法保护方法：实现对象的浅复制，只有实现了Cloneable接口才可以调用该方法，否则抛出CloneNotSupportedException异常
​	2、getClass()final方法：获得运行时类型。
​	3、toString方法该方法：用得比较多，一般子类都有覆盖。
​	4、finalize方法：该方法用于释放资源。因为无法确定该方法什么时候被调用，很少使用。
​	5、equals方法：该方法是非常重要的一个方法。一般equals和==是不一样的，但是在Object中两者是一样的。子类一般都要重写这个方法
​	6、hashCode方法：该方法用于哈希查找，可以减少在查找中使用equals的次数，重写了equals方法一般都要重写hashCode方法。这个方法在一些具有哈希功能的Collection中用到。
​	一般必须满足obj1.equals(obj2)==true。可以推出obj1.hashCode()==obj2.hashCode()，但是hashCode相等不一定就满足equals。不过为了提高效率，应该尽量使上面两个条件接近等价。
​	如果不重写hashCode(),在HashSet中添加两个equals的对象，会将两个对象都加入进去。
​	7、wait方法：wait方法就是使当前线程等待该对象的锁，当前线程必须是该对象的拥有者，也就是具有该对象的锁。wait()方法一直等待，直到获得锁或者被中断。wait(long timeout)设定一个超时间隔，如果在规定时间内没有获得锁就返回。

​	调用该方法后当前线程进入睡眠状态，直到以下事件发生。
```
（1）其他线程调用了该对象的notify方法
（2）其他线程调用了该对象的notifyAll方法。
（3）其他线程调用了interrupt中断该线程。
（4）时间间隔到了。
```
​	8、notify方法：该方法唤醒在该对象上等待的某个线程。
​	9、notifyAll方法：该方法唤醒在该对象上等待的所有线程。

###5、Java的四种引用，强弱软虚，用到的场景

​	（1）强引用：强引用不会被GC回收，并且在java.lang.ref里也没有实际的对应类型，平时工作接触的最多的就是强引用。（ Object obj = new Object();）
​	（2）软引用：内存空间足够，垃圾回收器就不会回收它，如果内存空间不足了，就会回收这些对象的内存
​	（3）弱引用：一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存
​	（4）幽灵引用(虚引用) ：虚引用必须和引用队列 （ReferenceQueue）联合使用；当垃圾回收器准备回收一个对象时**发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列**中。程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收

###6、 Hashcode的作用

​	（1）hashCode的存在主要是用于查找的快捷性，如Hashtable，HashMap等，hashCode是用来在散列存储结构中确定对象的存储地址的；
​	（2）如果两个对象相同，就是适用于equals(java.lang.Object) 方法，那么这两个对象的hashCode一定要相同；
​	（3）如果对象的equals方法被重写，那么对象的hashCode也尽量重写，并且产生hashCode使用的对象，一定要和equals方法中使用的一致，否则就会违反上面提到的第2点；
​	（4）两个对象的hashCode相同，并不一定表示两个对象就相同，也就是不一定适用于equals(java.lang.Object) 方法，只能够说明这两个对象在散列存储结构中，如Hashtable，他们**“存放在同一个篮子里”**。

###7、ArrayList、LinkedList、Vector的区别。

​	（1）、Arraylist和Vector是采用数组方式存储数据，此数组元素数大于实际存储的数据以便增加插入元素，都允许直接序号索引元素，但是插入数据要涉及到数组元素移动等内存操作，所以插入数据慢，查找有下标，所以查询数据快，
​	（2）、Vector由于使用了synchronized方法-线程安全，所以性能上比ArrayList要差，
​	（3）、LinkedList使用双向链表实现存储，按序号索引数据需要进行向前或向后遍历，但是插入数据时只需要记录本项前后项即可，插入数据较快。

###8、 String、StringBuffer与StringBuilder的区别

​	1、String字符串常量： String 是不可变的对象, 因此在每次对 String 
类型进行改变的时候其实都等同于生成了一个新的 String 对象，然后将指针指向新的 String 对象，所以经常改变内容的字符串最好不要用 String ，因为每次生成对象都会对系统性能产生影响，特别当内存中无引用对象多了以后， JVM 的 GC 就会开始工作，那速度是一定会相当慢的。
​	2、StringBuffer字符串变量（线程安全）
​	3、StringBuilder字符串变量（非线程安全）

###9、 Map、Set、List、Queue、Stack的特点与用法。
	如果涉及堆栈，队列等操作，建议使用List
	对于快速插入和删除元素的，建议使用LinkedList
	如果需要快速随机访问元素的，建议使用ArrayList
​	（1）Map:
```
Map是键值对，键Key是唯一不能重复的，一个键对应一个值，值可以重复。 
TreeMap可以保证顺序，HashMap不保证顺序，即为无序的。 
Map中可以将Key和Value单独抽取出来，其中KeySet()方法可以将所有的keys抽取正一个Set。而Values()方法可以将map中所有的values抽取成一个集合。	
```
​	（2）Set
```
不包含重复元素的集合，set中最多包含一个null元素
只能用Lterator实现单项遍历，Set中没有同步方法
```
​	（3）List
```
有序的可重复集合。
可以在任意位置增加删除元素。
用Iterator实现单向遍历，也可用ListIterator实现双向遍历
```
​	（4）Queue
```
Queue遵从先进先出原则。
使用时尽量避免add()和remove()方法,而是使用offer()来添加元素，使用poll()来移除元素，它的优点是可以通过返回值来判断是否成功。
LinkedList实现了Queue接口。
Queue通常不允许插入null元素。
```
​	（5）Stack
```
Stack遵从后进先出原则。
Stack继承自Vector。
它通过五个操作对类Vector进行扩展，允许将向量视为堆栈，它提供了通常的push和pop操作，以及取堆栈顶点的peek()方法、测试堆栈是否为空的empty方法等
```

### 10、HashMap和HashTable的区别。

​	（1）hashMap是非线程安全的，HashTable是线程安全的；HashMap可以接受为null的键值(key)和值(value)，而Hashtable则不行

​	（2） HashMap把Hashtable的contains方法去掉了，改成containsvalue和containsKey

​	（3）Hashtable继承自Dictionary类，而HashMap是Java1.2引进的Map interface的一个实现


| hashmap   | 线程不安全 | 允许有null的键和值  | 效率高一点、 | 方法不是Synchronize的要提供外同步 | 有containsvalue和containsKey方法 | HashMap 是Java1.2 引进的Map interface 的一个实现 | HashMap是Hashtable的轻量级实现 |
| --------- | ----- | ------------ | ------ | ---------------------- | ---------------------------- | --------------------------------------- | ----------------------- |
| hashtable | 线程安全  | 不允许有null的键和值 | 效率稍低、  | 方法是是Synchronize的       | 有contains方法方法                | 、Hashtable 继承于Dictionary 类              | Hashtable 比HashMap 要旧   |

###11、 HashMap和ConcurrentHashMap的区别，HashMap的底层源码。

​	ConcurrentHashMap采用分段锁

### 12、TreeMap、HashMap、LindedHashMap的区别。

​	（2）LinkedHashMap保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的.也可以在构造时用带参数，按照应用次数排序；也可以在构造时用带参数，按照应用次数排序

​	（3）TreeMap实现SortMap接口，能够把它保存的记录根据键排序,默认是按键值的升序排序，也可以指定排序的比较器，当用Iterator 遍历TreeMap时，得到的记录是排过序的TreeMap取出来的是排序后的键值对

​	 HashSet是通过HashMap实现的,TreeSet是通过TreeMap实现的,只不过Set用的只是Map的key

### 13、 Collection包结构，与Collections的区别。



| Collection  | 1、Collection是集合类的顶级接口；2、实现接口和类主要有Set、List、LinkedList、ArrayList、Vector、Stack、Set； |
| ----------- | ---------------------------------------- |
| Collections | 1、是针对集合类的一个帮助类，提供操作集合的工具方法；2、一系列静态方法实现对各种集合的搜索、排序、线程安全化等操作；3、服务于Java的Collection的框架； |

​	（1）、Collection是集合类的上级接口，子接口主要有Set 和List、Map

​	（2）Collections是针对集合类的一个帮助类，提供了操作集合的工具方法：一系列静态方法实现对各种集合的搜索、排序、线程安全化等操作

### 14、try catch finally，try里有return，finally还执行么？

```
运行时，在try中，要返回的结果已经准备好了，就在这个时候，程序跳到了finally，这个时候结果已经放到了x的局部表量中， 执行完finally后，再取出结果，finally对x进行了改变，但不会影响返回的结果。
```

<https://www.cnblogs.com/hongten/archive/2013/12/10/hongten_java_finally.html>

### 15、Excption与Error包结构。OOM你遇到过哪些情况，SOF你遇到过哪些情况。

<http://blog.csdn.net/l55iuming/article/details/53241613>

​	（1）什么是异常？**运行时发生的可被捕获和处理的错误。**

​	（2）Exception是程序本身可以处理的异常，这种异常分两大类运行时异常和非运行时异常

​		 Checked exception这类异常都是Exception的子类。从程序语法角度讲是必须进行处理的异常，如果不处理，程序就不能编译通过.如IOException、SQLException等以及用户自定义的Exception异常，一般情况下不自定义检查异常。

​		Unchecked exception这类异常都是RuntimeException的子类，虽然RuntimeException同样也是Exception的子类，但是它们是特殊的，它们不能通过client code来试图解决，所以称为Unchecked exception，如NullPointerException、IndexOutOfBoundsException等，这些异常是不检查异常，程序中可以选择捕获处理，也可以不处理。这些异常一般是由程序逻辑错误引起的，程序应该从逻辑角度尽可能避免这类异常的发生。

​	（3）Error是程序无法处理的错误，比如OutOfMemoryError、ThreadDeath等

### 16、java面向对象的三个特征与含义。

### 17、Override和Overload的含义去区别

### 18、 Interface与abstract类的区别。

### 19、Static class 与non static class的区别。

```
1、static与non-static是对立的。static应当（注意是应当）使用类名来引用。而non-static必须（是必须）使用对象实例名来引用。 
2、因为static、non-static的数据相关性，static只能引用类的static数据成员；而non-static既可以引用类的static数据成员，也可以引用对象自身的数据
3、static与non-static method在overload方面是一样的
4、static方法是与类相关的，不是通过this引用的，所以它不能被override。其引用在编译期就得确定。而non-static方法才有可能被override
5、static与abstract，它们不能同时用于修饰一个方法。因为abstract的语义就是说这个方法是多态方法，需要subclass的实现。而static方法则是在本类中实现的，编译期绑定，不具有多态行为
6、static与interface，interface中的method也不能是static的。理由同上。
```



### 20、 java多态的实现原理。

### 21、 实现多线程的两种方法：Thread与Runable。

### 22、线程同步的方法：sychronized、lock、reentrantLock等。

### 23、锁的等级：方法锁、对象锁、类锁。

### 24、写出生产者消费者模式。

### 25、ThreadLocal的设计理念与作用。

```
ThreadLocal和其它所有的同步机制都是为了解决多线程中的对同一变量的访问冲突

线程局部变量（ThreadLocal）其实的功用非常简单，就是为每一个使用该变量的线程都提供一个变量值的副本，是每一个线程都可以独立地改变自己的副本，而不会和其它线程的副本冲突。

在线程是活动的并且ThreadLocal对象是可访问的时，该线程就持有一个到该线程局部变量副本的隐含引用，当该线程运行结束后，该线程拥有的所有线程局部变量的副本都将失效，并等待垃圾收集器收集。

同步机制是为了同步多个线程对相同资源的并发访问，是为了多个线程之间进行通信的有效方式；而ThreadLocal是隔离多个线程的数据共享，从根本上就不在多个线程之间共享资源（变量），这样当然不需要对多个线程进行同步了。

```



### 26、ThreadPool用法与优势。

```
合理利用线程池能够带来三个好处。
	第一：降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗
	第二：提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行
	第三：提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控
	
	new  ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, milliseconds,runnableTaskQueue, handler);
```



### 27、Concurrent包里的其他东西：ArrayBlockingQueue、CountDownLatch等等。

### 28、 wait()和sleep()的区别。

### 29、 foreach与正常for循环效率对比。

```
ArrayList  for速度快
linkList   foreach速度快
```



### 30、 Java IO与NIO。

### 31、 反射的作用于原理。

​        JAVA反射（放射）机制：“程序运行时，允许改变程序结构或变量类型，这种语言称为动态语言”

```
在运行时判断任意一个对象所属的类；
在运行时构造任意一个类的对象；
在运行时判断任意一个类所具有的成员变量和方法；
在运行时调用任意一个对象的方法；
生成动态代理。
```

​	Java的反射机制是在编译并不确定是哪个类被加载了，而是在程序运行的时候才加载、探知、自审。使用在编译期并不知道的类。这样的特点就是反射

	什么是JAVA的反射 <https://www.cnblogs.com/dongguacai/p/6535417.html>
		1、在运行状态中，对于任意一个类，都能够知道这个类的属性和方法。
		2、对于任意一个对象，都能够调用它的任何方法和属性。
<http://blog.csdn.net/maguanghui_2012/article/details/69372209>

http://blog.csdn.net/baidu_18607183/article/details/40980551

http://blog.csdn.net/ritterliu/article/details/7764849

### 32、泛型常用特点，List<String>能否转为List<Object>。

### 33、解析XML的几种方式的原理与特点：DOM、SAX、PULL。

### 34、java与C++对比。

### 35、 Java1.7与1.8新特性。

http://www.cnblogs.com/invoker-/p/6896865.html#autoid-3-6-0

### 36、设计模式：单例、工厂、适配器、责任链、观察者等等。

### 37、JNI的使用。

