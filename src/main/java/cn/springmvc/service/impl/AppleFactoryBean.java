package cn.springmvc.service.impl;

import cn.springmvc.model.AppleBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by gwb on 2018/6/22.
 */
@Component
public class AppleFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new AppleBean();
    }

    @Override
    public Class<?> getObjectType() {
        return AppleBean.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
