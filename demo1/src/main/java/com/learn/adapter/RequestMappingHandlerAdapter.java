package com.learn.adapter;

import com.learn.handler.HttpServletHandler;
import com.learn.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 专门来处理HttpServletHandler处理器类型
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        return false;
    }

    @Override
    public ModelAndView handleRequest(Object handler, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        return null;
    }
}
