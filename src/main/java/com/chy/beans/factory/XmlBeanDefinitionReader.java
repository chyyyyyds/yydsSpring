package com.chy.beans.factory;

import com.chy.BeanDefinition;
import com.chy.core.Resource;
import org.dom4j.Element;

public class XmlBeanDefinitionReader {
    BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String id = element.attributeValue("id");
            String aClass = element.attributeValue("class");
            beanFactory.registerBeanDefinition(new BeanDefinition(id, aClass));
        }


    }
}
