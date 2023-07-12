package com.example.app.config.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
interface LoggingCommon {

  void springBeanPointcut();

  void controllerExecutionLogger();

  void applicationServicesPointcut();

  void applicationRepositoriesPointcut();

  void throwControllerException(JoinPoint joinPoint, Throwable e);

  void throwServiceException(JoinPoint joinPoint, Throwable e);

  void throwRepositoryException(JoinPoint joinPoint, Throwable e);

  void beforeControllerExecutionAdvice(JoinPoint joinPoint);

  void afterControllerExecutionAdvice(JoinPoint joinPoint);

  void afterReturningControllerAdvice(JoinPoint joinPoint, ResponseEntity response);

  Object logAround(ProceedingJoinPoint joinPoint) throws Throwable;

  boolean registerUsername(String username);

}
