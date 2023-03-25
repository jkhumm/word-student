package com.mode.technology.exception;

import com.mode.technology.context.AppContext;
import com.mode.technology.enums.HttpCodeEnum;
import com.mode.technology.vo.tip.Tip;
import com.mode.technology.vo.tip.TipUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @author heian
 * @date 2022/6/20 23:43
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler implements ResponseBodyAdvice {

    @ExceptionHandler({Exception.class})
    public Tip<?> exceptionHandler(Exception e) {
        log.error("打印错误", e);

        // 处理自定义异常
        if ((e instanceof CustomException)) {
            CustomException c = (CustomException) e;
            if (c.getCode() != 0){
                return TipUtil.error(c.getCode(),c.getMessage());
            }
            return TipUtil.error(c.getMessage());
        }

        // 请求体错误
        if ((e instanceof HttpMessageNotReadableException)) {
            return TipUtil.error(HttpCodeEnum.REQUIRED_REQUEST_BODY_IS_MISSING);
        }

        // 方法参数类型不匹配异常
        if ((e instanceof MethodArgumentTypeMismatchException)) {
            return TipUtil.error(HttpCodeEnum.METHOD_ARGUMENT_TYPE_MISMATCH);
        }

        // 参数异常
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException m = (MethodArgumentNotValidException) e;
            String defaultMessage = m.getBindingResult().getFieldError().getDefaultMessage();
            if(StringUtils.isBlank(defaultMessage)){
                defaultMessage=m.getBindingResult().getFieldError().getField() + " 字段参数异常或者为空!";
            }
            return TipUtil.error(defaultMessage);
        }

        // 请求方式
        if ((e instanceof HttpRequestMethodNotSupportedException)) {
            HttpRequestMethodNotSupportedException h = (HttpRequestMethodNotSupportedException) e;
            StringBuilder sb = new StringBuilder();
            sb.append("不支持");
            sb.append(h.getMethod());
            sb.append("请求方法，");
            sb.append("支持");
            String[] methods = h.getSupportedMethods();
            if (methods != null) {
                for (String str : methods) {
                    sb.append(str);
                }
            }
            log.error(sb.toString(), e);
            return TipUtil.error(sb.toString());
        }

        //运行时异常
        if ((e instanceof RuntimeException)) {
            RuntimeException h = (RuntimeException) e;
            return TipUtil.error(500,h.getMessage());
        }

        AppContext.getRequestContext().getHttpServletResponse().setStatus(500);
        return TipUtil.error(HttpCodeEnum.ERROR);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return o;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }
}


