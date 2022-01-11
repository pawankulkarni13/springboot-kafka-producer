package com.stark.springbootkafkaproducer.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private Environment environment;

    private Integer maxTry;
    private Integer executions = 0;

    public LoggingAspect(Environment environment) {
        this.maxTry = Integer.valueOf(environment.getProperty("maxTry"));
    }

    @Around("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        if(maxTry==executions){
            System.out.println("Exiting Scheduler and Stopping Kafka Event Producer Application !");
            System.exit(0);
        }
        executions++;
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        if (executionTime > 1000) {
            System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms ----- attention");
        } else {
            System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        }

        return proceed;
    }

}
