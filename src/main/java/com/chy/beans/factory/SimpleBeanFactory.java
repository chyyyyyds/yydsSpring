package com.chy.beans.factory;

import com.chy.BeanDefinition;
import com.chy.exception.BeansException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBeanFactory implements BeanFactory {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();
    private List<String> beanNames = new ArrayList<>();
    private Map<String, Object> singletons = new HashMap<>();

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanDefinitions.add(beanDefinition);
        this.beanNames.add(beanDefinition.getId());
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object o = singletons.get(beanName);
        if (o != null) {
            return o;
        }
        int i = beanNames.indexOf(beanName);
        if (i == -1) {
            throw new BeansException();
        }

        BeanDefinition beanDefinition = beanDefinitions.get(i);
        try {
            Class<?> aClass = Class.forName(beanDefinition.getClassName());
            o = aClass.newInstance();
            singletons.put(beanDefinition.getId(), o);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }


}
