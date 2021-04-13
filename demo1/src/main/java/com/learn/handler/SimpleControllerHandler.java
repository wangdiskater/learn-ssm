package com.learn.handler;

import com.learn.model.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 制定一种处理器的标准
 */
public interface SimpleControllerHandler {
    ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp)  throws Exception;
}
