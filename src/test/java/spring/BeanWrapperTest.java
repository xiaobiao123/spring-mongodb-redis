package spring;

import cn.springmvc.model.User;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Spring通过BeanWrapper类封装一个javabean的行为，可以设置和获取其属性值
 */
public class BeanWrapperTest {

    public static void main(String[] args) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(new User());
        System.out.println("getPropertyValue："+beanWrapper.getPropertyValue("nickname"));

        System.out.println("getPropertyType："+beanWrapper.getPropertyType("nickname"));
        beanWrapper.setPropertyValue("nickname","今天天气很不错");
        System.out.println(beanWrapper.getPropertyValue("nickname"));

    }
}
