package com.lagou.config;

import com.lagou.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL转换封装核心类
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 20:44
 */
public class BoundSql {
    
    private String parseSql;
    
    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String parseSql, List<ParameterMapping> parameterMappingList) {
        this.parseSql = parseSql;
        this.parameterMappingList = parameterMappingList;
    }

    public String getParseSql() {
        return parseSql;
    }

    public void setParseSql(String parseSql) {
        this.parseSql = parseSql;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }

    @Override
    public String toString() {
        return "BoundSql{" +
                "parseSql='" + parseSql + '\'' +
                ", parameterMappingList=" + parameterMappingList +
                '}';
    }
}
