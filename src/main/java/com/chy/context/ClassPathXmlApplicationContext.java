package com.chy.context;

import com.chy.BeanDefinition;
import com.chy.beans.factory.BeanFactory;
import com.chy.beans.factory.SimpleBeanFactory;
import com.chy.beans.factory.XmlBeanDefinitionReader;
import com.chy.core.ClassPathXmlResource;
import com.chy.core.Resource;
import com.chy.exception.BeansException;

public class ClassPathXmlApplicationContext implements BeanFactory{
   private BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        Resource resource = new ClassPathXmlResource(fileName);
        BeanFactory simpleBeanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(simpleBeanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = simpleBeanFactory;
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return beanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanDefinition);
    }
}
