package other.集合;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.*;

/**
 * @program: service_gcs
 * @description: 集合类测试
 * @author: Mr.Guo
 * @create: 2018-11-16 13:31
 **/
public class ConllectionTest {
    @Test
    public void testArrayList(){
        //List<String> list=new ArrayList<>();
        //list.add("xxxxxx");
        //list.add(3,"xxxxxxxx");
        //
        //
        //List linkedList=new LinkedList();
        //linkedList.add("xxxxxx");

//        String a="123l";
//        String a1="123l";
//        System.out.println(a==a1);
//
//        Long l1=127l;
//        Long l2=127l;
//
//        System.out.println(l1==l2);
//
//        Double d1=12d;
//        Double d2=12d;
//        System.out.println(d1==d2);

        Map<String,Map<String,Object>> code= Maps.newHashMap();
        Map<String,Object> user=Maps.newHashMap();
        user.put("userId","用户id");
        user.put("deviceCode","设备编号");
        user.put("deviceCype","设备类型");
        user.put("openId","微信服务/订阅号id");
        user.put("extraJson","扩展信息");

        System.out.println(JSONObject.toJSONString(user));

    }
}
