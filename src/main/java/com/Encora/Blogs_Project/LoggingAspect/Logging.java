package com.Encora.Blogs_Project.LoggingAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
 
@Aspect
@Component
public class Logging {
    private static final Logger logger = LoggerFactory.getLogger(Logging.class);
 
    @Around(" execution(* com.Encora.Blogs_Project.BlogerController.*(..))"+"||"+" execution(* com.Encora.Blogs_Project.Blog_service.*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
 
        String methodName = joinPoint.getSignature().toShortString();
        logger.error("Kiran--- Entering method: {}", methodName);
 
        long startTime = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
 
            logger.info("Kiran--- Exiting method: {}, Execution time: {} ms", methodName, duration);
            return result;
        } catch (Throwable throwable) {
            logger.error("Exception in method: {}, Error: {}", methodName, throwable.getMessage());
            throw throwable;
        }
    }
}