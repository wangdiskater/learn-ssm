package com.learn.core.builder;


import com.learn.core.config.Configuration;
import com.learn.core.factory.DefaultSqlSessionFactory;
import com.learn.core.factory.SqlSessionFactory;
import com.learn.core.utils.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream){

        // 获取配置文件对应的Document对象
        Document document = DocumentUtils.createDocument(inputStream);

        // 通过字节流封装Configuration
        XMLConfigBuilder configBuilder = new XMLConfigBuilder();
        Configuration configuration = configBuilder.parseCofiguration(document.getRootElement());
        return build(configuration);
    }

    public SqlSessionFactory build(Reader reader){
        // 通过字符流封装Configuration
        Configuration configuration = null;
        return build(configuration);
    }

    private SqlSessionFactory build(Configuration configuration){

        return new DefaultSqlSessionFactory(configuration);
    }

}
