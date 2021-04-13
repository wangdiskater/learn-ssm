package com.learn.adapter;

import com.learn.handler.SimpleControllerHandler;
import com.learn.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 专门来处理SimpleControllerHandler处理器类型
 */
public class SimpleControllerHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof SimpleControllerHandler);
    }

    @Override
    public ModelAndView handleRequest(Object handler, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return ((SimpleControllerHandler)handler).handleRequest(req,resp);
    }
}
