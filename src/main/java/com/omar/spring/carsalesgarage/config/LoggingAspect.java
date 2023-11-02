package com.omar.spring.carsalesgarage.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.omar.spring.carsalesgarage.service.*.*(..))")
    public void serviceMethods() {
    }

    @Before("serviceMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        logger.info("Entering: " + joinPoint.getSignature().toShortString());
        logger.info("Input arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("serviceMethods()")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;

        logger.info("Exiting: " + joinPoint.getSignature().toShortString());
        logger.info("Output result: " + result);
        logger.info("Execution time: " + executionTime + "ms");

        return result;
    }
}
