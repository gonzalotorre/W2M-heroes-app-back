package com.heroes.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExecutionTimeTracker {

    @Around("@annotation(com.heroes.annotation.ExecutionTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object o = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Method " + joinPoint.getSignature() + " took " + (endTime - startTime) + " milliseconds to execute.");
        return o;
    }

}
