package com.learn.mapping;


import com.learn.handler.QueryUserHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <bean name="/queryUser" class="com.kaikeba.handler.QueryUserHandler" />
 */
public class RequestMappingHandlerMapping implements HandlerMapping {
    private Map<String, Object> urlHandlers = new HashMap<>();

    public RequestMappingHandlerMapping() {
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
