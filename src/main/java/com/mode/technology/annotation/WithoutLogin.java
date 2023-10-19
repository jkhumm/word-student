package com.mode.technology.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * 未标记此注解的所有接口调用都需要登录权限
 * @author heian
 */
@Target(value = {ElementType.METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WithoutLogin {

}
