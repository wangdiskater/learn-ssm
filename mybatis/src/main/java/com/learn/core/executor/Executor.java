package com.learn.core.executor;


import com.learn.core.config.MappedStatement;

import java.util.List;

public interface Executor {

    <T> List<T> query(MappedStatement mappedStatement, Object param);
}
