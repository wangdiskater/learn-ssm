
package com.learn.core.utils;


import com.learn.core.sqlsource.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理#{}中的参数的
 *
 * @author 灭霸詹
 *
 */

public class ParameterMappingTokenHandler implements TokenHandler {
	// 存储#{}解析出来的参数信息
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

	// content是参数名称
	// content 就是#{}中的内容
	@Override
	public String handleToken(String content) {
		parameterMappings.add(buildParameterMapping(content));
		return "?";
	}

	private ParameterMapping buildParameterMapping(String content) {
		ParameterMapping parameterMapping = new ParameterMapping(content);
		return parameterMapping;
	}

	public List<ParameterMapping> getParameterMappings() {
		return parameterMappings;
	}

	public void setParameterMappings(List<ParameterMapping> parameterMappings) {
		this.parameterMappings = parameterMappings;
	}

}
