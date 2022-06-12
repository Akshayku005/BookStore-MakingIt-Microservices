package com.bridgelabz.orderservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AOPService {

    //logs method before hitting API
    @Before("execution(* com.bridgelabz.orderservice.controller.OrderController.*(..))")     //point-cut expression
    public void logBeforeV1(JoinPoint joinPoint) {
        log.info("Executing API : " + joinPoint.getSignature().getName() + " ");
    }

    //logs method After hitting API
    @After("execution(* com.bridgelabz.orderservice.controller.OrderController.*(..))")     //point-cut expression
    public void logAfter(JoinPoint joinPoint) {
        log.info("API successfully Executed : " + joinPoint.getSignature().getName() + " ");
    }

    //logs parameters After hitting API
    @After("execution(* com.bridgelabz.orderservice.controller.OrderController.*(..))")
    public void logAfterAndSaveArgs(JoinPoint joinPoint) {
        log.info("After : " + Arrays.toString(joinPoint.getArgs()));
    }
//    TimeTaken
    @Around(value = "execution(* com.bridgelabz.orderservice.controller.OrderController.*(..))")
    public Object timeTracker(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime=System.currentTimeMillis();
            Object obj=joinPoint.proceed();
            long timeTaken=System.currentTimeMillis()-startTime;
            log.info("Time taken by {} is {}",joinPoint,timeTaken);
            return obj;
    }
}
