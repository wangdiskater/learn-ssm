package com.learn.mapping;


import com.learn.handler.QueryUserHandler;
import com.learn.handler.SaveUserHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <bean name="/queryUser" class="com.kaikeba.handler.QueryUserHandler" />
 */
public class BeanNameUrlHandlerMapping implements HandlerMapping {
    private Map<String, Object> urlHandlers = new HashMap<>();

    public BeanNameUrlHandlerMapping() {
        // 方式1：BeanNameUrlHandlerMapping
        // <bean name="/queryUser" class="处理器类的全路径"/>
        // TODO
        this.urlHandlers.put("/queryUser",new QueryUserHandler());
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String uri = request.getRequestURI();
        if (uri == null || "".equals(uri)){
            return null;
        }
        return this.urlHandlers.get(uri);
    }
}
