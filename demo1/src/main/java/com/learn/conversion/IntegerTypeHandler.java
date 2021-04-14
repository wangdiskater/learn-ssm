package com.learn.conversion;

public class IntegerTypeHandler implements TypeHandler{
    @Override
    public Object handleValue(String[] valueArray) {
        return Integer.parseInt(valueArray[0]);
    }
}
