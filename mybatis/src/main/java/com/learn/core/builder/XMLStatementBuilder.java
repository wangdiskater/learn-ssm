package com.learn.core.builder;


import com.learn.core.config.Configuration;
import com.learn.core.config.MappedStatement;
import com.learn.core.sqlsource.SqlSource;
import org.dom4j.Element;

public class XMLStatementBuilder {

    private Configuration configuration;
    public XMLStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseStatement(String namespace, Element selectElement) {
        String id = selectElement.attributeValue("id");
        String statementId = namespace+"."+id;
        String resultType = selectElement.attributeValue("resultType");
        String statementType = selectElement.attributeValue("statementType");

        // 封装SqlSource数据
        SqlSource sqlSource = createSqlSource(selectElement);

        // 封装MappedStatement
        MappedStatement mappedStatement = new MappedStatement(statementId,resultType,statementType,sqlSource);

        configuration.addMappedStatement(statementId,mappedStatement);

    }
    private SqlSource createSqlSource(Element selectElement) {

        XMLScriptBuilder scriptBuilder = new XMLScriptBuilder();
        SqlSource sqlSource = scriptBuilder.parseScriptNode(selectElement);
        return sqlSource;
    }

}
