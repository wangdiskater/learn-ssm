package com.learn.core.builder;

import com.learn.core.config.Configuration;
import org.dom4j.Element;

import java.util.List;

/**
 * 专门用来解析映射文件
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseMapper(Element rootElement) {
        String namespace = rootElement.attributeValue("namespace");

        // TODO 映射文件中除了select标签还有很多其他的标签

        List<Element> selectElements = rootElement.elements("select");
        for (Element selectElement : selectElements) {
            XMLStatementBuilder statementBuilder = new XMLStatementBuilder(configuration);
            statementBuilder.parseStatement(namespace,selectElement);
        }

    }
}
