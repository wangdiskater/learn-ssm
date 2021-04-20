package com.learn.controller;


import com.learn.annotation.Controller;
import com.learn.annotation.RequestMapping;
import com.learn.annotation.ResponseBody;
import com.learn.core.factory.SqlSessionFactory;
import com.learn.core.sqlsessoin.SqlSession;
import com.learn.factory.DefaultFactory;
import com.learn.mymybatis.MybatisUtils;
import com.learn.po.User;
import com.learn.spring.factory.support.DefaultListableBeanFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注解方式开发的处理器，谁是真正的处理器？？？
 * 回答1：使用@Controller标记类是处理器（false）
 * 回答2：使用@Controller标记的类中使用@RequestMapping标记的方法（true）
 *       HandlerMethod才是注解方式开发方式真正的处理器
 *       包含：Controller对象和Method对象
 *
 * 1、需要解析RequestMapping注解中的请求URL，建立HandlerMethod与请求的映射关系
 * 2、通过HandlerMethod封装的数据，借助于反射技术，完成方法调用
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("query")
    @ResponseBody
    public Map<String, Object> query(Integer id, String name){
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);

        return map;
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(){
        return "添加成功";
    }


    @RequestMapping("query2")
    @ResponseBody
    public List query2(String username, String sex){
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        map.put("sex",sex);

        DefaultListableBeanFactory beanFactory = DefaultFactory.beanFactory;
        MybatisUtils mybatisUtils = (MybatisUtils) beanFactory.getBean("MybatisUtils");
        SqlSessionFactory sqlSessionFactory = mybatisUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("test.queryUserByParams", map);

        return users;
    }
}
