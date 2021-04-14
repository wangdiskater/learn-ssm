package com.learn.spring.registry;

import com.learn.spring.ioc.BeanDefinition;

import java.util.List;

public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String beanName);

    List<BeanDefinition> getBeanDefinitions();

    void registerBeanDefinition(String beanName, BeanDefinition bd);
}
