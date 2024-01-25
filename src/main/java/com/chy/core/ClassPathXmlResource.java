package com.chy.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

public class ClassPathXmlResource implements Resource{
    Document document;
    Element rootElement;
    Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName) {
        SAXReader reader =new SAXReader();
        URL url = ClassLoader.getSystemClassLoader().getResource(fileName);
        try {
            this.document  = reader.read(url);
            this.rootElement  = (Element) document.getRootElement();
            this.elementIterator = this.rootElement.elementIterator();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return this.elementIterator.next();
    }
}
