package com.learn.spring.reader;


import com.learn.spring.registry.BeanDefinitionRegistry;
import com.learn.spring.resource.Resource;
import com.learn.spring.utils.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * 它主要是针对流对象进行读取
 */
public class XmlBeanDefinitionReader {
    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream inputStream = resource.getResource();

        doLoadBeanDefinitions(inputStream);
    }

    public void loadBeanDefinitions(String location) {
        // 根据location获取合适的Resource对象
        // ResourceLoader/ResourcePatternResolver
        Resource resource = null;
        loadBeanDefinitions(resource);
    }

    private void doLoadBeanDefinitions(InputStream inputStream) {
        // 创建文档对象
        Document document = DocumentUtils.getDocument(inputStream);
        BeanDefinitionDocumentReader documentReader = new DefaultBeanDefinitionDocumentReader(registry);
        documentReader.registerBeanDefinitions(document.getRootElement());
    }
}
