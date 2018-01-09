package com.bppleman.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class BGAspect {
    public  static Logger logger = Logger.getLogger(BGAspect.class);

    @Around("execution(String com.bppleman.controller.bg..*(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {

//        System.out.println("@Around：执行目标方法之前...");

        //访问目标方法的request参数：

        Object[] args = point.getArgs();
        HttpServletRequest request = null;
        for (Object o : args) {
            if (o instanceof HttpServletRequest) {
                request = (HttpServletRequest) o;
            }
        }
//        如果没有HttpServletRequest类型的参数，则打印一个错误日志标明是哪个方法
        if (request == null) {
            Signature sig = point.getSignature();
            MethodSignature msig = null;
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("该注解只能用于方法");
            }
            msig = (MethodSignature) sig;
            Object target = point.getTarget();
            Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            logger.error("该方法没有request参数" + currentMethod);
        } else {
            String uri = request.getRequestURI();
            String[] keyWords = {"problem", "contest", "exam"};
            String collapse = null;
            for (String keyWord : keyWords) {
                if (uri.indexOf(keyWord) != -1) {
                    collapse = keyWord;
                    break;
                }
            }
            request.setAttribute("collapse", collapse);
        }
        //用改变后的参数执行目标方法

        Object returnValue = point.proceed(args);

//        System.out.println("@Around：执行目标方法之后...");

//        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());

        return returnValue;
    }

    @Before("execution(String com.bppleman.controller.bg..*(..))")
    public void setCollapseIn(JoinPoint joinPoint) {
//        System.out.println("BGAspect.setCollapseIn");
    }
}
