package spring;

import cn.springmvc.model.User;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.swing.table.TableRowSorter;
import java.util.Map;

/**
 * Spring通过BeanWrapper类封装一个javabean的行为，可以设置和获取其属性值
 */
public class BeanWrapperTest {

    public static void main(String[] args) {
        User user = new User();
        BeanWrapper beanWrapper = new BeanWrapperImpl(user);
        System.out.println("getPropertyValue：" + beanWrapper.getPropertyValue("nickname"));

        System.out.println("getPropertyType：" + beanWrapper.getPropertyType("nickname"));
        beanWrapper.setPropertyValue("nickname", "今天天气很不错");
        System.out.println(beanWrapper.getPropertyValue("nickname"));
        //返回的是一个类
        try {
            String c = (String) Class.forName("java.lang.String").newInstance();

            String str = (String) Class.forName("java.lang.String", true, ClassLoader.getSystemClassLoader())
                    .newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //Class.forName(xxx.xx.xx)的作用是要求JVM查找并加载指定的类

        Women women = new Women();

        women.map.put("1", "1");
        women.map.put("2", "2");

    }

    static class Women {
        private final Map<String, Object> map = Maps.newHashMap();
    }
}
