package com.chy.beans.factory;

import com.chy.BeanDefinition;
import com.chy.exception.BeansException;

public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    void registerBeanDefinition(BeanDefinition beanDefinition);
}
