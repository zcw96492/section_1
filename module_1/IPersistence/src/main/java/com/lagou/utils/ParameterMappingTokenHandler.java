package com.lagou.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 标记处理类,用于解析SQL语句中的#{}
 * @author zhouchaowei 
 * @date 2020年07月01日 星期三 20:44
 */
public class ParameterMappingTokenHandler implements TokenHandler {
	
	private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();

	/**
	 * 
	 * @param content 是参数名称,要处理的SQL语句中#{}的属性值
	 * @return
	 */
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
