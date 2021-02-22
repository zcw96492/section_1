/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.lagou.utils;

/**
 * 标记处理接口
 * @author zhouchaowei
 * @date 2020年07月01日 星期三 20:44
 */
public interface TokenHandler {

    /**
     * 标记处理接口方法
     * @param content 要处理的SQL语句
     * @return
     */
    String handleToken(String content);
}

