package com.example.app.config.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingExecutionAspect {

  public static final Logger logger = LoggerFactory.getLogger(LoggingExecutionAspect.class);

  /**
   * This method uses Around advice which ensures that an advice can run before and after the method
   * execution, to and log the execution time of the method This advice will be be applied to all
   * the method which are annotate with the annotation @LogExecutionTime @see
   * com.example.springaop.logging.LogExecutionTime
   * <p>
   * Any mehtod where execution times need to be measue and log, anotate the method with
   *
   * @param proceedingJoinPoint
   * @return
   * @throws Throwable
   * @LogExecutionTime example
   * @LogExecutionTime public void m1();
   */
  @Around("@annotation(com.example.app.config.observability.LogExecutionTime)")
  public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

    // Get intercepted method details
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();

    // Measure method execution time
    StopWatch stopWatch = new StopWatch(className + "->" + methodName);
    stopWatch.start(methodName);
    Object result = proceedingJoinPoint.proceed();
    stopWatch.stop();
    // Log method execution time
    if (logger.isInfoEnabled()) {
      logger.info(stopWatch.prettyPrint());
    }
    return result;
  }
}
