package com.lagou.utils;

/**
 * 参数处理类模型
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 20:44
 */
public class ParameterMapping {

    /** 解析出来的参数名称 */
    private String content;

    public ParameterMapping(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
