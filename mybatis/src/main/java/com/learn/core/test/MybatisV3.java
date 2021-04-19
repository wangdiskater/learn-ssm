package com.learn.core.test;


import com.learn.core.builder.SqlSessionFactoryBuilder;
import com.learn.core.factory.SqlSessionFactory;
import com.learn.core.io.Resources;
import com.learn.core.sqlsessoin.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.以面向对象的思维去改造mybatis手写框架 2.将手写的mybatis的代码封装一个通用的框架（java工程）给程序员使用
 *
 * @author 灭霸詹
 *
 * 暗号：剑道万古如长夜
 */

public class MybatisV3 {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before(){
        // SqlSessionFactory的创建流程（完成Configuration的封装流程）
        String location = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(location);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test(){
        // SqlSession的执行流程(完成JDBC的执行流程)
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Map map = new HashMap();
        map.put("username","千年老亚瑟");
        map.put("sex","男");
        List<User> users = sqlSession.selectList("test.queryUserByParams", map);
        System.out.println(users);
    }
}

