package com.bridgelabz.userservice.aop;

import com.bridgelabz.userservice.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AOPService {

    //logs method before hitting API
    @Before("execution(* com.bridgelabz.userservice.controller.UserRegistrationController.*(..))")     //point-cut expression
    public void logBeforeV1(JoinPoint joinPoint) {
        log.info("Executing API : " + joinPoint.getSignature().getName() + " ");
    }

    //logs method After hitting API
    @After("execution(* com.bridgelabz.userservice.controller.UserRegistrationController.*(..))")     //point-cut expression
    public void logAfter(JoinPoint joinPoint) {
        log.info("API successfully Executed : " + joinPoint.getSignature().getName() + " ");
    }
    //ExceptionHandling
    @Around(value = "execution(* com.bridgelabz.userservice.service.UserService.*(..))")
    public Object taskHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object obj = joinPoint.proceed();
            return obj;
        } catch (BookException exception) {
            log.info(" TaskException StatusCode  {}", exception.getHttpStatus().value());
            log.info("TaskException Message {}", exception.getMessage());
            throw new ResponseStatusException(exception.getHttpStatus(), exception.getMessage());
        }
    }
    //logs parameters After hitting API
    @After("execution(* com.bridgelabz.userservice.controller.UserRegistrationController.*(..))")
    public void logAfterAndSaveArgs(JoinPoint joinPoint) {
        log.info("After : " + Arrays.toString(joinPoint.getArgs()));
    }
//    TimeTaken
    @Around(value = "execution(* com.bridgelabz.userservice.controller.UserRegistrationController.*(..))")
    public Object timeTracker(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime=System.currentTimeMillis();
            Object obj=joinPoint.proceed();
            long timeTaken=System.currentTimeMillis()-startTime;
            log.info("Time taken by {} is {}",joinPoint,timeTaken);
            return obj;
    }
}
