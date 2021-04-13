package com.learn.servlet;

import com.learn.adapter.HandlerAdapter;
import com.learn.adapter.HttpServletHandlerAdapter;
import com.learn.adapter.SimpleControllerHandlerAdapter;
import com.learn.mapping.BeanNameUrlHandlerMapping;
import com.learn.mapping.HandlerMapping;
import com.learn.mapping.SimpleUrlHandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @ClassName DispatcherServlet
 * @Description todo
 * @Author wangdi
 * @Date 2021/4/14 0:13
 **/

public class DispatcherServlet extends AbstractServlet{

    // 存放所有adapter
    private List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    // request - handler映射
    private List<HandlerMapping> handlerMappings = new ArrayList<>();

    /**
     * 功能描述 重写init方法把adapter 放入
     * @author wangdi
     * @date   2021/4/14 0:24
     * @param
     * @return void
     */
    @Override
    public void init() throws ServletException {
        handlerAdapters.add(new HttpServletHandlerAdapter());
        handlerAdapters.add(new SimpleControllerHandlerAdapter());


        // mapping
        handlerMappings.add(new BeanNameUrlHandlerMapping());
        handlerMappings.add(new SimpleUrlHandlerMapping());

    }

    /**
     * 功能描述
     * 分发请求三步：
     *
     *  1、根据请求查找对应的【处理器】进行处理（处理器是什么？如何查找处理器？）
     *
     *
     *  2、根据处理器查找对应的适配器
     *
     *
     *  3.返回
     *
     *
     * @author wangdi
     * @date   2021/4/14 0:15
     * @param req
     * @param resp
     * @return void
     */
    @Override
    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1、根据请求查找对应的【处理器】进行处理（处理器是什么？如何查找处理器？）
            Object handler = getHandler(req);
            if (handler == null){
                return;
            }

            // 2、根据处理器查找对应的适配器 -- handler 需要区分是返回数据还是modelAndVide所以需要区分
            // 适配器（DispatcherServlet-- 适配器 -->handler）
            // 适配器和处理器是一对一的
            HandlerAdapter ha = getHandlerAdapter(handler);
            if (ha == null){
                return;
            }

            // 执行处理器的处理逻辑，并返回处理结果 （通过处理器适配器去执行处理器）
            ha.handleRequest(handler,req,resp);
            /*if (handler instanceof HttpServletHandler){
                ((HttpServletHandler)handler).handleRequest(req,resp);
            }
*/
            // 将处理结果通过response响应给客户端
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 功能描述 通过request 获取handler
     * @author wangdi
     * @date   2021/4/14 0:26
     * @param req
     * @return java.lang.Object
     */
    private Object getHandler(HttpServletRequest req) {

        //根据处理器和请求的映射关系进行查找（映射关系可能存储在xml配置文件的标签中，可能存储到map集合中）
        // 方式1：BeanNameUrlHandlerMapping
        // <bean name="/queryUser" class="处理器类的全路径"/>

        // 方式2：SimpleUrlHandlerMapping
        // <bean class="专门用来建立映射关系的类">
        //  <props>
        //    <prop key="请求URL">处理器类的全路径</prop>
        //  </props>
        // </bean>


        // 先去方式1中查找
        // 如果找不到，则继续找
        // 再去方式2中查找
        // 如果找不到，则继续找

        if(handlerMappings != null){
            for (HandlerMapping hm : handlerMappings) {
                Object handler = hm.getHandler(req);
                if (handler!=null){
                    return handler;
                }
            }
        }

        return null;
    }




    /**
     * 功能描述 通过handler拿adapter 处理请求
     * @author wangdi
     * @date   2021/4/14 0:22
     * @param handler
     * @return com.learn.adapter.HandlerAdapter
     */
    private HandlerAdapter getHandlerAdapter(Object handler) {
        // 这就是策略模式的玩法，替换掉很多if语句进行判断的方式
        if (handlerAdapters != null){
            for (HandlerAdapter ha : handlerAdapters) {
                // 需要根据处理器类型去判断哪个适配器和它适配
                if (ha.supports(handler)) {
                    // 返回对应的适配器
                    return ha;
                }
            }
        }
        return null;
    }
}
