//package javastudy.jdk8;
//
//import cn.springmvc.model.User;
//import com.google.common.base.Function;
//import com.google.common.base.Predicate;
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//
//import java.util.*;
//import java.util.function.BiConsumer;
//import java.util.function.Consumer;
//import java.util.function.DoubleToIntFunction;
//import java.util.function.IntBinaryOperator;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
///**
// * Created by gwb on 2017/12/7.
// */
//public class LambdaTest {
//    public static void main(String[] args) {
//        List<String> items = new ArrayList<>();
//        items.add("E");
//        items.add("E");
//        items.add("E");
//        items.add("A");
//        items.add("B");
//        items.add("C");
//        items.add("D");
//        items.add("E");
//        items.add("E");
//        items.add("E");
//        items.add("E");
//        //输出：A,B,C,D,E
//        items.forEach(item -> System.out.println(item));
//        //
//        ////输出 : C
//        //items.forEach(item -> {
//        //    if ("C".equals(item)) {
//        //        System.out.println(item);
//        //    }
//        //});
//
//        Map<String, String> treeMap = new HashMap<>();
//        treeMap.put("name", "name");
//        treeMap.put("age", "age");
//
//
//        treeMap.forEach((k, v) -> {
//            System.out.println("");
//        });
//
//
//        treeMap.forEach((k, v) -> {
//            System.out.println("Item : " + k + " Count : " + v);
//            if ("E".equals(k)) {
//                System.out.println("Hello E");
//            }
//        });
//
//        //lambda表达式替换匿名类，使用() -> {}代码块替代了整个匿名类
//        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
//
//
//        //(params) -> expression
//        //(params) -> statement
//        //(params) -> { statements }
//
//        //例如，如果你的方法不对参数进行修改、重写，只是在控制台打印点东西的话，那么可以这样写：
//        Runnable runnable = () -> System.out.println("Hello Lambda Expressions");
//        runnable.run();
//
//        //如果你的方法接收两个参数，那么可以写成如下这样：
//        IntBinaryOperator intBinaryOperator = (int even, int odd) -> even + odd;
//        intBinaryOperator.applyAsInt(1, 2);
//
//        IntBinaryOperator intBinaryOperator1 = (int a, int b) -> a + b;
//
//        Maps.EntryTransformer<String, String, Object> xxxxxxxxx = (String a, String b) -> {
//            System.out.println("xxxxxxxxx");
//            return a + b;
//        };
//        String o = (String) xxxxxxxxx.transformEntry("", "");
//
//        /*params=参数,your Code=你的代码,results=返回值
//        (params,params) -> {your Code}*/
//
//        /*如果只有一个参数或者一行代码的话 可以将括号省略掉，于是就变成了
//        params -> your Code*/
//
//        Consumer<String> tConsumer = a -> System.out.println("tConsumer" + a);
//        tConsumer.accept("xxxx");
//
//        /*如果是有返回值是的话是*/
//        Predicate ftFunction = (params) -> {
//            return true;
//        };
//
//
//        /*同样，这种return只有一行的话, 也可以简写，例如
//        x -> x + 1
//        这就等效于
//        (x) ->{return x + 1}*/
//
//        long count = items.stream().filter(item -> item.equals("A"))//选出所有来自伦敦的艺术家
//                .count();//统计他们的数量
//        System.out.println("count:" + count);
//
//        //获取流对象
//       /* 对于集合来说,直接通过stream()方法即可获取流对象
//        List<Person> list = new ArrayList<Person>();
//        Stream<Person> stream = list.stream();*/
//
//       /* 对于数组来说,通过Arrays类提供的静态函数stream()获取数组的流对象
//        String[] names = {"chaimm","peter","john"};
//        Stream<String> stream = Arrays.stream(names);*/
//
//        /*直接将几个普通的数值变成流对象*/
//        Stream<String> stream = Stream.of("chaimm","peter","john");
//
//        //惰性求值与及早求值
//
//
//        /*list.stream()//将集合转化成流
//                .???()//一系列惰性求值的操作,返回值为stream
//                .collect(toList())//及早求值，这个及早求值的方法返回值为集合,再将流转化*/
//
//        // 去重distinc
//        Stream<String> stream2 = items.stream();
//        List<String> stringList = stream2.distinct().collect(Collectors.toList());
//        System.out.println(stringList);
//
//        //筛选filter
//        System.out.println(items.stream().filter(item -> item.equals("e")).count());
//
//
//        //截取limit 截取流的前N个元素
//        System.out.println(items.stream().limit(5).distinct().count());
//
//        //跳过skip  跳过流的前N个元素：
//
//
//        //映射map
//        List<User> userList = Lists.newArrayList();
//
//        for (int i = 0; i < 5; i++) {
//            User user = new User();
//            user.setName("name" + i);
//            user.setAge(100 - i);
//            userList.add(user);
//        }
//        List<String> artistNames = userList.stream()
//                .filter(artist -> artist.getName().startsWith("name"))
//                .map(artist -> artist.getName())//将艺术家集合映射成了包含艺术家名字的集合
//                .collect(Collectors.toList());
//        System.out.println(artistNames);
//
//
//        //合拼
//        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
//                .flatMap(numbers -> numbers.stream())
//                .collect(Collectors.toList());
//        together.forEach(n -> System.out.println(n));
//        //输出结果为1,2,3,4
//
//
//        //获取集合中最大  最小值max和min
//        User theMaxAgeArtist = userList.stream()
//                .min(Comparator.comparing(artist -> artist.getAge()))
//                .get();
//    }
//
//
//}
