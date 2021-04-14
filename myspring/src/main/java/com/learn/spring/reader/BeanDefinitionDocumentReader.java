package com.learn.spring.reader;

import org.dom4j.Element;

public interface BeanDefinitionDocumentReader {
    public void registerBeanDefinitions(Element rootElement);
}
