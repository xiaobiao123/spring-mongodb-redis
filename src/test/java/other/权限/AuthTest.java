package other.权限;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import other.权限.menu.GetTree;

import java.math.BigInteger;
import java.util.*;

@Controller
@RestController
public class AuthTest {

    //key:menuid, value:菜单名称
    private static Map<String, String> menu = new HashMap<String, String>();
    //key:userid, value:操作权限
    private static Map<String, String> user = new HashMap<String, String>();

    static {
        menu.put("1", "用户管理");
        menu.put("2", "数据字典");
        menu.put("3", "订单管理");
        user.put("zhangsan", "1");
        user.put("lisi", "0");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

//        authorize("zhangsan", "1");
//        authorize("zhangsan", "2");
        authorize("zhangsan", "100");
//        System.out.println("===================================");
//        checkAll("zhangsan");
//        checkAll("lisi");
//        System.out.println("===================================");
//        revoke("zhangsan", "1");
//        checkAll("zhangsan");
//        System.out.println("===================================");
//        revoke("lisi", "1");
//        checkAll("lisi");
    }

    /**
     * 赋权限
     */
    private static void authorize(String userid, String menuid) {
        String rights = user.get(userid); //存的是36进制
        BigInteger b36 = new BigInteger(rights, 36);
        b36 = b36.setBit(Integer.parseInt(menuid));
        b36 = b36.setBit(Integer.parseInt("2"));
        user.put(userid, b36.toString(36));
        System.out.println("用户[" + userid + "]被赋予了[" + menu.get(menuid) + "]的访问权限");
    }

    /**
     * 回收权限
     */
    private static void revoke(String userid, String menuid) {
        String rights = user.get(userid); //存的是36进制
        BigInteger b36 = new BigInteger(rights, 36);
        b36 = b36.clearBit(Integer.parseInt(menuid));
        user.put(userid, b36.toString(36));
        System.out.println("用户[" + userid + "]被收回了[" + menu.get(menuid) + "]的访问权限");
    }

    /**
     * 检查用户是否有某一个菜单的访问权限
     */
    private static void check(String userid, String menuid) {
        String rights = user.get(userid); //存的是36进制
        BigInteger b36 = new BigInteger(rights, 36);
        boolean flag = b36.testBit(Integer.parseInt(menuid));
        System.out.println("用户[" + userid + "]" + (flag ? "有" : "没有") + "访问[" + menu.get(menuid) + "]other.权限");
    }

    /**
     * 查询用户所有的访问权限
     */
    private static void checkAll(String userid) {
        for (Iterator<String> it = menu.keySet().iterator(); it.hasNext(); ) {
            check(userid, it.next());
        }
    }

    @Test
    public void testBig() {
        Long start = System.currentTimeMillis();
        BigInteger bigInteger = new BigInteger("0", 36);
        System.out.println(bigInteger.testBit(1));
        bigInteger = bigInteger.setBit(Integer.parseInt("1"));
        bigInteger = bigInteger.setBit(Integer.parseInt("2"));
        bigInteger = bigInteger.setBit(Integer.parseInt("3"));

        for (int i = 0; i < 1000; i++) {
            bigInteger = bigInteger.setBit(i);
            // System.out.println(bigInteger.testBit(i));
        }

        System.out.println(bigInteger);
        System.out.println(System.currentTimeMillis() - start);

//        System.out.println(bigInteger.testBit(1));
//        System.out.println(bigInteger.testBit(2));
//        System.out.println(bigInteger.testBit(0));
//
//
//        new BigInteger("123",30);
//        BigInteger b36 = new BigInteger("1");
//        System.out.println(b36.setBit(Integer.parseInt("1")));
//        b36 = b36.setBit(Integer.parseInt("1"));
//        b36 = b36.setBit(Integer.parseInt("2"));
//        System.out.println(b36.testBit(1));
//        b36 = b36.clearBit(1);
//        System.out.println(b36.testBit(1));

    }


    @Test
    public void testBigTest() {
        //计算和
        BigInteger menuIds = new BigInteger("0");
        for (int id = 0; id < 10; id++) {
            menuIds = menuIds.setBit(id);
        }
        menuIds = menuIds.setBit(1);
        menuIds = menuIds.setBit(1);
        menuIds = menuIds.setBit(1);
        //2^1
        //System.out.println(num);
        //2^2+2^1
        menuIds = menuIds.setBit(2);
        //System.out.println(num);
        //2^3+2^2+2^1

        System.out.println(menuIds.testBit(2));
        System.out.println(menuIds.testBit(4));
        //System.out.println(num);
        //System.out.println(num.testBit(3));
        int sum = (int) (Math.pow(2, 1) + Math.pow(2, 2) + Math.pow(2, 3));
        System.out.println(sum);
        // 00000010 00000100   00001000
        //   2   4  8
        // 00001110
        System.out.println(sum & (int) (Math.pow(2, 3)));


    }


    class Session {
        public Object user;
        public List<BigInteger> menuIds;

        public Object getUser() {
            return user;
        }

        public void setUser(Object user) {
            this.user = user;
        }

        public List<BigInteger> getMenuIds() {
            return menuIds;
        }

        public void setMenuIds(List<BigInteger> menuIds) {
            this.menuIds = menuIds;
        }
    }

    @Test
    public void testSession() {
//        Session session=new Session();
//        session.setUser(new Object());
//        session.setMenuIds(new ArrayList<BigInteger>());
//
//        String url="url"; //入参
//        BigInteger menuId= getMenuIdByUrl(url);//根据url得到menuId
//        List<BigInteger> menuIds = session.getMenuIds();
//        Boolean falg=false;
//        for (BigInteger bigInteger:menuIds){
//            falg= bigInteger.testBit(menuId);//权限验证
//            if (falg){
//                break;
//            }
//        }
//
//        if (false){
//            //执行业务代码
//        }

        GetTree getTree = new GetTree();
        ResultData resultData = new ResultData();
        resultData.setCode("0000");
        resultData.setMsg("执行成功");
        resultData.setDataJson(getTree.getTree());

        System.out.println(JSONObject.toJSONString(resultData));

    }


    class ResultData {
        public String code;
        public String msg;
        public Object dataJson;


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getDataJson() {
            return dataJson;
        }

        public void setDataJson(Object dataJson) {
            this.dataJson = dataJson;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    @Test
    public void testAon() {
//        Element element =new Element("21","12","name","url");
//        String name = element.getClass().getName();
//
//        Annotation cont = UserController.class.getAnnotation(Controller.class);
//        Annotation mapping = UserController.class.getAnnotation(RequestMapping.class);
//
//        Method mdMapping = element.getClass().getMethod(RequestMapping.class);
//        Method mdRightDefintion = element.getClass().getMethod(RightDefintion.class);


//
//        for (Method method:methods) {
//            if (method.getName().startsWith("get"));{
//                System.out.println("");
//                System.out.println(method.getAnnotation(RequestMapping.class));
//
//            }
//        }


//        //保存API和权限的关系
//        Map<String,Map<String,Set>> mapMap=new HashMap<>();
//        //meun
//        Set meunSet =new HashSet();
//        meunSet.add("menu_token1");
//        meunSet.add("menu_token2");
//        Map<String,Set> menuMap=new HashMap<>();
//        menuMap.put("meuns", meunSet);
//        //right
//        Set rightSet =new HashSet();
//        rightSet.add("right_token1");
//        rightSet.add("right_token2");
//        menuMap.put("rights",rightSet);
//        mapMap.put("user/addUser",menuMap);

        //System.out.println(JSONObject.toJSONString(mapMap));

        //用户和功能点的关系


//        Map<String, Map<String, Set>> objectMap = new HashMap<>();
//
//
//        Set menuSet = new HashSet();
//        Set rightSet = new HashSet<>();
//
//        menuSet.add("menuToken");
//        menuSet.add("menuToken1");
//
//        rightSet.add("funToken1");
//        rightSet.add("funToken12");
//
//
//        Map<String, Set> map = new HashMap<>();
//
//        map.put("menuSet", menuSet);
//        map.put("rightSet", rightSet);
//
//        objectMap.put("userNo", map);
//
//
//        Set menuSet1 = map.get("menuSet");
//        Set rightSet1 = map.get("rightSet");
//
//        Iterator iterator = menuSet1.iterator();
//        while (iterator.hasNext()){
//            Object next = iterator.next();
//            //System.out.println(next);
//        }


//        System.out.println(JSONObject.toJSONString(objectMap));
        Map<String, Object> urlMap = new HashMap<>();
        Set<String> urlSet = new HashSet<>();
        urlSet.add("url1");
        urlSet.add("url2");


        urlMap.put("userNo", urlSet);
        System.out.println(JSONObject.toJSONString(urlMap));
//
//
//
//        Map<String, Set> url = mapMap.get("url");
//
//        Set roleToken = url.get("roleToken");
//
//        Set menuTokenToken = url.get("menuTokenToken");
//        //验证通过，登录就可访问
//        if (roleToken==null&&menuTokenToken==null){
//
//        }else  if (roleToken!=null&& menuTokenToken==null){
//            //判断用户是否拥有角色
//        }else if (roleToken==null&& menuTokenToken==null){
//            //判断用户是否有访问菜单权限
//        }else if (roleToken=!null&& menuTokenToken=!null){
//            //判断用户是否同时拥有角色和菜单访问权限
//        }else {
//            throw
//        }


        //
    }
}
