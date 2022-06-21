package com.mode.technology.filter;

import com.mode.technology.constants.RedisConstant;
import com.mode.technology.context.AppContext;
import com.mode.technology.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 绑定上下文
 */

@WebFilter(urlPatterns = "/*", asyncSupported = true)
@Slf4j
@Component
public class BindContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    //先走过滤器再走切面
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //放行相关接口
        HttpServletRequest req = (HttpServletRequest) request;
        Long begin = System.currentTimeMillis();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //初始化上下文信息： 请求/响应对象、请求token
        RequestContext rc = new RequestContext();
        rc.setHttpServletRequest(req);
        rc.setHttpServletResponse((HttpServletResponse) response);
        // 绑定token: 从请求头、cookie、request参数中获取token
        String token = getToken(req);
        rc.setToken(token);
        // 绑定上线问信息到当前线程栈
        AppContext.setRequestContext(rc);
        chain.doFilter(request, response);
        Long end = System.currentTimeMillis();
        Long cost = end - begin;
        log.info("token: {}, uid: {}, uri: {}, cost: {}, method: {}", token, rc.getUid(), req.getRequestURI(), cost, req.getMethod());
        AppContext.removeRequestContext();
    }

    @Override
    public void destroy() {
    }

    // 从请求头、cookie、request参数中获取token
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (null == token) {
            token = request.getParameter("token");
        }
        if (null == token) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (RedisConstant.TOKEN.equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        if (null == token) {
            token = (String) request.getSession().getAttribute("token");
        }
        return token;
    }

}

