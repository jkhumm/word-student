package com.mode.technology.aspect;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

@Slf4j
@Component
@Aspect
public class MicroMeterAspect {
	// 后面加入直方图是为了记录整个时间段的调用，方便统计接口的波峰波谷
    private final Timer.Builder timer = Timer.builder("server.timer")
            .description("服务耗时").publishPercentileHistogram();

    private MeterRegistry registry;

    @Value("${spring.application.name}")
    private String applicationName;

    private String ip;

    // 注入自身定义的指标统计
    @Autowired
    public MicroMeterAspect(MeterRegistry meterRegistry) {
        this.registry = meterRegistry;
    }

    @PostConstruct
    public void check() {
        String ip;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress();
        } catch (UnknownHostException ignore) {
            ip = "unKnown";
        }
        this.ip = ip;
    }
    @Pointcut(value = "@annotation(com.mode.technology.annotation.MyMonitor)||@within(com.mode.technology.annotation.MyMonitor)")
    public void myPointcut() {
        // @annotation 对方法拦截 @within 对类拦截
    }

    @Around(value = "myPointcut()")
    public Object microMeter(ProceedingJoinPoint point) throws Throwable {
        long timestamp = System.currentTimeMillis();
        // 方法全路径接口名
        String className = point.getTarget().getClass().getCanonicalName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        String interfaceName = className + "." + signature.getMethod().getName();
        Object result = point.proceed();
        try {
            Duration duration = Duration.ofMillis(System.currentTimeMillis() - timestamp);
            Iterable<Tag> tags = Tags.of(
                    "interfaceName", interfaceName,
                    "applicationName", applicationName,
                    "ip", ip
            );
            timer.tags(tags).register(registry).record(duration);
        } catch (Exception e) {
            log.error("统计发生异常:", e.getCause());
        }
        return result;
    }


}