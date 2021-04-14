package com.learn.model;

import java.lang.reflect.Method;

/**
 * 功能描述 一个路径代表一个handler
 * @author wangdi
 * @date   2021/4/15 1:00
 * @return
 */
public class HandlerMethod {
    private Object controller;

    private Method method;

    public HandlerMethod(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
