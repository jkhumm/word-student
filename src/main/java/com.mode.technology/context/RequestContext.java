package com.mode.technology.context;

import com.mode.technology.vo.beans.UserBean;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求上下文
 * @author heian
 * @since JDK 1.8
 */

@Data
public class RequestContext {

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 登录token
     */
    private String token;

    /**
     * 用户实体
     */
    private UserBean currentUser;


    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;

}
