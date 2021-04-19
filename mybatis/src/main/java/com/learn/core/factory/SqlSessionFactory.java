package com.learn.core.factory;


import com.learn.core.sqlsessoin.SqlSession;

public interface SqlSessionFactory {

    SqlSession openSession();
}
