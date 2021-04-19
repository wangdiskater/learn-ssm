package com.learn.core.factory;


import com.learn.core.config.Configuration;
import com.learn.core.sqlsessoin.DefaultSqlSession;
import com.learn.core.sqlsessoin.SqlSession;

/**
 * 使用的是工厂方法设计模式
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
