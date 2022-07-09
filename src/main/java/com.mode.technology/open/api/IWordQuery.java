package com.mode.technology.open.api;

import com.mode.technology.open.vo.WordQueryReq;

/**
 * @author heian
 * @date 2022/6/23 0:05
 */
public interface IWordQuery {

    /**
     * 根据输入 查询 输出结果
     */
    public String wordQuery(WordQueryReq req);


}
