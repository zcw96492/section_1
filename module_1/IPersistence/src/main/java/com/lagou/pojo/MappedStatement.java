package com.lagou.pojo;

/**
 * 【容器对象】自定义Mapper.xml解析类
 * 【解析UserMapper.xml】
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 10:32
 */
public class MappedStatement {
    
    /** id标识 */
    private String id;
    /** 返回值类型 */
    private String resultType;
    /** 参数值类型 */
    private String parameterType;
    /** SQL语句 */
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
