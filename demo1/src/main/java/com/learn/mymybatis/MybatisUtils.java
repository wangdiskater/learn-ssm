package com.learn.mymybatis;

import com.learn.core.builder.SqlSessionFactoryBuilder;
import com.learn.core.factory.SqlSessionFactory;
import com.learn.core.io.Resources;

import java.io.InputStream;

/**
 * @Description
 * @ClassName MybatisSql
 * @Author wangDi
 * @date 2021-04-20 10:13
 */
public class MybatisUtils {

    private SqlSessionFactory sqlSessionFactory;

    public void init(){
        // SqlSessionFactory的创建流程（完成Configuration的封装流程）
        String location = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(location);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
