package com.learn.spring.aware;


import com.learn.spring.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory);
}
