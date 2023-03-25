package com.mode.technology.aspect;

import com.alibaba.fastjson.JSONObject;
import com.mode.technology.annotation.WithoutLogin;
import com.mode.technology.constants.RedisConstant;
import com.mode.technology.context.AppContext;
import com.mode.technology.context.RequestContext;
import com.mode.technology.enums.HttpCodeEnum;
import com.mode.technology.exception.CustomException;
import com.mode.technology.util.RedisUtil;
import com.mode.technology.vo.beans.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.lang.reflect.Method;

@Order(1)
@Aspect
@Component
@Slf4j
public class AuthAspect extends HandlerInterceptorAdapter {


    @Autowired
    private RedisUtil redisUtil;

    // 对接口设置拦截切点
    @Pointcut("execution(* com.mode.technology.controller..*.*(..))")
    public void aspect() {
    }


    /**
     * 对所有接口进行鉴权，并将接口信息绑定上下文
     */
    @Around("aspect()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        RequestContext rc = AppContext.getRequestContext();
        //并将用户信息绑定到当前上下文
        String token = rc.getToken();
        if (StringUtils.isNotBlank(token)) {
            String jsonStr = (String) redisUtil.get(RedisConstant.TOKEN + token);
            if (StringUtils.isNotBlank(jsonStr)) {
                UserBean user = JSONObject.parseObject(jsonStr, UserBean.class);
                rc.setUid(user.getId());
                rc.setCurrentUser(user);
            }
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        WithoutLogin withoutLogin = method.getDeclaredAnnotation(WithoutLogin.class);
        // 未携带此接口 提示错误
        if (null == withoutLogin && null == rc.getUid()) {
            throw new CustomException(HttpCodeEnum.USERNAME_OR_PASSWORD_ERR);
        }
        return point.proceed();
    }


}
