package com.learn.core.sqlsessoin;


import com.learn.core.config.Configuration;
import com.learn.core.config.MappedStatement;
import com.learn.core.sqlsource.BoundSql;
import com.learn.core.sqlsource.ParameterMapping;
import com.learn.core.sqlsource.SqlSource;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultSqlSession implements SqlSession{
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public  <T> List<T> selectList(String statementId, Object param) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        List<T> results = new ArrayList<>();

        try {
            MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
            // 获取连接
            connection = getConnection();
            // 执行SqlSource中的方法获取SQL语句（需要去解析#{}和${}）
            SqlSource sqlSource = mappedStatement.getSqlSource();
            BoundSql boundSql = sqlSource.getBoundSql(param);
            String sql = boundSql.getSql();
            // 获取StatementType,创建Statement
            statement = createStatement(mappedStatement.getStatementType(),connection,sql);
            // 设置参数
            parameterize(statement,param,boundSql);
            // 执行Statement
            results = query(statement,mappedStatement);
            // 结果映射
            return results;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 8、释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


    @Override
    public <T> T selectOne(String statementId, Object param) {
        return null;
    }

    private Connection getConnection() throws Exception{
        DataSource dataSource = configuration.getDataSource();
        return dataSource.getConnection();
    }
    private Statement createStatement(String statementType, Connection connection, String sql) throws Exception{

        if (statementType.equals("prepared")){
            return connection.prepareStatement(sql);
        }else if (statementType.equals("callable")){
            return connection.prepareCall(sql);
        }else{
            return connection.createStatement();
        }
    }
    private void parameterize(Statement statement, Object param, BoundSql boundSql) throws Exception{
        if (statement instanceof PreparedStatement){
            PreparedStatement preparedStatement = (PreparedStatement) statement;

            if (param instanceof Integer){
                preparedStatement.setObject(1,param);
            }else if (param instanceof String){
                preparedStatement.setObject(1,param);
            }else if (param instanceof Map){
                Map map = (Map) param;

                //需要进行SQL解析之后，才会处理该部分内容,需要解析#{}才会得到参数列表
                List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
                for (int i = 0 ; i<parameterMappings.size() ;i++) {
                    ParameterMapping parameterMapping = parameterMappings.get(i);
                    String name = parameterMapping.getName();
                    Object value = map.get(name);

                    preparedStatement.setObject(i+1,value);
                }
            }
        }
    }
    private <T> List<T> query(Statement statement, MappedStatement mappedStatement) throws Exception{
        List<Object> results = new ArrayList<>();

        if (statement instanceof PreparedStatement){
            PreparedStatement preparedStatement = (PreparedStatement) statement;
            ResultSet rs = preparedStatement.executeQuery();
            Object result = null;

            String resultType = mappedStatement.getResultType();
            Class clazz = Class.forName(resultType);
            while (rs.next()) {
                Constructor constructor = clazz.getConstructor();
                result = constructor.newInstance();
                ResultSetMetaData metaData = rs.getMetaData();
                // 结果集中列的数量
                int columnCount = metaData.getColumnCount();
                for (int i = 0;i<columnCount ;i++){
                    String columnName = metaData.getColumnName(i + 1);
                    // 给对象赋属性值
                    // 查询结果中的列名和要映射的对象的属性名必须一致
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(result,rs.getObject(i+1));
                }

                results.add(result);
            }
        }
        return (List<T>) results;
    }
}
