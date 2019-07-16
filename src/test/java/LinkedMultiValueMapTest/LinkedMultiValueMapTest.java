package LinkedMultiValueMapTest;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.elasticsearch.common.recycler.Recycler;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.LinkedMultiValueMap;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class LinkedMultiValueMapTest {
    public static void main(String[] args) {
        LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<>();


        linkedMultiValueMap.add("name", "t1");
        linkedMultiValueMap.add("name", "t2");
        linkedMultiValueMap.add("name", "t3");
        linkedMultiValueMap.add("narrrrme", "t3");

        for (Map.Entry<String, List<String>> entry : linkedMultiValueMap.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());

        }


    }

    public List<TradePaymentModel> list;

    @Before
    public void InitModel() {
        list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            TradePaymentModel model = new TradePaymentModel();
            model.setShouldPayment(i);
            model.setName("name" + i);
            model.setAge("100" + i);
            model.setSex("xxxxxxxx");
            list.add(model);
        }
    }


    @Test
    public void testStream() {
        //flatMap
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5));

        //创建一个 装有两个泛型为integer的集合
        Stream<List<Integer>> streamss = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5));
        // 将两个合为一个
        Stream<Integer> integerStream = streamss.flatMap(
                (Function<List<Integer>, Stream<Integer>>) integers -> integers.stream());
        // 为新的集合
        List<Integer> collectss = integerStream.collect(Collectors.toList());


    }

    @Test
    public void testFindFirst() {
        Optional<TradePaymentModel> first   = list.stream().filter(model -> model.getShouldPayment() > 3).findFirst();
        boolean                     present = first.isPresent();
        TradePaymentModel tradePaymentModel = list.stream().filter(model -> model.getShouldPayment() > 3).findFirst().orElse(null);
        System.out.println(JSONObject.toJSONString(tradePaymentModel));
    }


    @Test
    public void testSkipLimst() {
//        list.forEach(user -> System.out.println(JSONObject.toJSONString(user)));
        System.out.println("************skip*****************limit**********");
        list.stream().sorted(Comparator.comparing(TradePaymentModel::getShouldPayment).reversed()).skip(6).limit(3).forEach(user -> System.out.println(JSONObject.toJSONString(user)));
        System.out.println("************distinct*****************distinct**********");
        list.stream().distinct();
    }

    @Test
    public void testReversed() {
        //排序  默认正序，倒序：reversed()
        List<TradePaymentModel> collect = list.stream().sorted(Comparator.comparing(TradePaymentModel::getShouldPayment)).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(collect));
    }

    @Test
    public void testFilterSum() {
        //排除1
        int sum = list.stream().filter(model -> !model.getShouldPayment().equals(1))//排除主支付单
                .mapToInt(TradePaymentModel::getShouldPayment).sum();//sum 应付金额
        System.out.println(sum);

    }

    @Test
    public void testMapReturn() {
        Stream<Map<String, String>> mapStream = list.stream().map(model -> {
            Map<String, String> map1 = Maps.newHashMap();
            map1.put(model.getName(), model.getSex());
            return map1;
        });
        System.out.println(mapStream);
    }

    /**
     * map()：接收一个方法作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     */
    @Test
    public void testToMap() {
        //dtoPageResult.getList().stream().map(dto -> {
        //   return BimGoodsModelConverter.INSTANCE.dto2vo(dto);
        //}).collect(Collectors.toList());

        //to List String
        List<Integer> collect1 = list.stream().filter(model -> model.getShouldPayment().equals(8)).map(TradePaymentModel::getShouldPayment).collect(Collectors.toList());

        System.out.println("map" + JSONObject.toJSONString(collect1));

        //to hashMap
        Map<String, String> map = list.stream().collect(toMap(TradePaymentModel::getAge, TradePaymentModel::getName));

        System.out.println(map);
        Map<String, TradePaymentModel> mapp = list.stream().collect(toMap(TradePaymentModel::getAge, Function.identity()));

        Map<String, String> mappp = list.stream().collect(toMap(TradePaymentModel::getAge, TradePaymentModel::getName, (k1, k2) -> k1));

        System.out.println(mappp);
        // to List String
        List<String> collect = list.stream().map(model -> {
            Map<String, String> map1 = Maps.newHashMap();
            map1.put(model.getName(), model.getSex());
            return model.getName();
        }).collect(Collectors.toList());

        list.stream().map(mode -> mode.getName() + mode.getShouldPayment()).map(t -> t);
    }


    /**
     * //Arrays.stream(array)获取的是一个IntStream对象，boxed 方法用于将目前 Stream 中的基本类型装箱
     */
    @Test
    public void testStreamBoxed() {
        String[]       array  = {"are", "you", "ok"};
        Stream<String> stream = Arrays.stream(array);
        //对于基本类型数组的处理
        int[]           array22   = {1, 2, 3, 4, 5};
        Stream<Integer> stream223 = Arrays.stream(array22).boxed();

    }


}
