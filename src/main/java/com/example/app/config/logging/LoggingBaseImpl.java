package com.example.app.config.logging;

import static com.example.app.constants.RequestMappings.HEALTH_CHECK_ROOT;
import static org.apache.naming.ResourceRef.AUTH;

import com.example.app.constants.LoggingConstants;
import com.example.app.controller.app.healthcheck.HealthCheckController;
import com.example.app.service.UserSecurityContextService;
import com.example.app.util.DateUtils;
import com.example.app.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Component
@Slf4j
public abstract class LoggingBaseImpl extends AbstractLogging implements LoggingCommon {

  private final UserSecurityContextService userSecurityContextService;

  private final DateUtils dateUtils;

  private final com.example.app.util.StringUtils stringUtils;

  private final JwtUtils jwtUtils;

  private final String userKey = "username";

  public LoggingBaseImpl(Environment env, UserSecurityContextService userSecurityContextService,
      DateUtils dateUtils, com.example.app.util.StringUtils stringUtils, JwtUtils jwtUtils) {
    this.userSecurityContextService = userSecurityContextService;
    this.dateUtils = dateUtils;
    this.stringUtils = stringUtils;
    this.jwtUtils = jwtUtils;
    this.env = env;
  }

  /**
   * Pointcut that matches all repositories, services and Web REST endpoints.
   */
  @Override
  @Pointcut("within(org.springframework.web.client.RestTemplate..*)")
  public void springBeanPointcut() {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  @Override
  @Pointcut("execution(public   * com.example.app.controller..*(..))")
  public void controllerExecutionLogger() {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  /**
   * Pointcut that matches all services in the application's main packages.
   */
  @Override
  @Pointcut("execution(public   * com.example.app.service..*(..))")
  public void applicationServicesPointcut() {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  /**
   * Pointcut that matches all repositories in the application's main packages.
   */
  @Override
  @Pointcut("execution(public   * com.example.app.repository..*(..))")
  public void applicationRepositoriesPointcut() {
    // Method is empty as this is just a Pointcut, the implementations are in the advices.
  }

  @Override
  @AfterThrowing(pointcut = "controllerExecutionLogger()", throwing = "e")
  public void throwControllerException(JoinPoint joinPoint, Throwable e) {
    e.setStackTrace(new StackTraceElement[0]);
    log.warn(LoggingConstants.EXCEPTION + "in controller :: ", e);
  }

  /**
   * Advice that logs methods throwing exceptions.
   *
   * @param joinPoint join point for advice
   * @param e         exception
   */
  @Override
  @AfterThrowing(pointcut = "applicationServicesPointcut()", throwing = "e")
  public void throwServiceException(JoinPoint joinPoint, Throwable e) {
    log.warn(LoggingConstants.EXCEPTION + "in service :: ");
    log.info(
        "Method:  {}::{} ",
        joinPoint.getTarget().getClass().getName(),
        joinPoint.getSignature().getName()
    );
    StackTraceElement[] trace = e.getStackTrace();
    int length = trace.length;
    if (length > 0) {
      log.warn("stack trace length : " + length);
    }
    Throwable filteredThrowable = stackTraceRetainOnlyAppClasses(e);
    if (filteredThrowable.getStackTrace().length > 0) {
      log.error(LoggingConstants.EXCEPTION, filteredThrowable);
    } else {
      log.error(LoggingConstants.EXCEPTION, e);
    }
    e.setStackTrace(new StackTraceElement[0]);
  }

  @Override
  @AfterThrowing(pointcut = "applicationRepositoriesPointcut()", throwing = "e")
  public void throwRepositoryException(JoinPoint joinPoint, Throwable e) {
    log.warn(LoggingConstants.EXCEPTION + "in repository :: ");
    log.info(
        "Method:  {}::{} ",
        joinPoint.getTarget().getClass().getName(),
        joinPoint.getSignature().getName()
    );
    StackTraceElement[] trace = e.getStackTrace();
    int length = trace.length;
    if (length > 0) {
      log.warn("stack trace length : " + length);
    }
    Throwable filteredThrowable = stackTraceRetainOnlyAppClasses(e);
    if (filteredThrowable.getStackTrace().length > 0) {
      log.error(LoggingConstants.EXCEPTION, filteredThrowable);
    } else {
      log.error(LoggingConstants.EXCEPTION, e);
    }
    e.setStackTrace(new StackTraceElement[0]);
  }

  @Override
  @Before("controllerExecutionLogger()")
  public void beforeControllerExecutionAdvice(JoinPoint joinPoint) {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .currentRequestAttributes()).getRequest();

    String userId = StringUtils.EMPTY;
    Map<String, String> headers = userSecurityContextService.getRequestHeaders(request);
    String userPrincipalData = userSecurityContextService.getUserPrincipalInfo();

    if ((headers.containsKey(AUTH.toLowerCase()) || headers.containsKey(AUTH) || headers
        .containsKey("authorization")) && userPrincipalData != null) {

      String auth = headers.get("authorization").split(" ")[1];
      userId = jwtUtils.getSubFormJWT(auth);
    }

    HttpSession session = request.getSession(false);
    StringBuilder sessionLog = new StringBuilder();
    if (session != null) {
      // it's valid
      try {
        long sessionCreationTime = session.getCreationTime();
        String sessionId = session.getId();
        sessionLog.append("session ").append(sessionId).append(", timestamp ").append(dateUtils
            .millsToBaseFormattedDate(sessionCreationTime));
      } catch (IllegalStateException illegalStateException) {
        sessionLog.append("no valid session");
      }
    } else {
      sessionLog.append(request.getRequestedSessionId());
    }

    if (!request.getServletPath().contains(HEALTH_CHECK_ROOT)) {
      final String remoteAddr =
          stringUtils.countOccurrences(request.getRemoteAddr(), '0') > 5 ? headers.get("host")
              : request.getRemoteAddr().concat(" ").concat(request.getRemoteAddr());
      log.info(
          this.generateTitle("Request")
              + nl + "Ip:  " + remoteAddr
              + nl + "Session:  " + sessionLog
              + nl + "User:  " + userId
              + nl + "Headers: " + headers
              + nl + "Content-Type Header: " + request.getHeader("Content-Type")
              + nl + "HTTP method: " + request.getMethod()
              + nl + "Path: " + request.getServletPath()
              + nl + "Arguments:  " + Arrays.toString(joinPoint.getArgs()) + nl
      );
    }
  }

  @Override
  @After("controllerExecutionLogger()")
  public void afterControllerExecutionAdvice(JoinPoint joinPoint) {
    if (!joinPoint.getTarget().getClass().getName()
        .contains(HealthCheckController.class.getSimpleName())) {
      log.info(
          "Method:  {}::{}",
          joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName()
      );
    }
  }

  /**
   * Advice that logs when a method is entered and exited.
   *
   * @param joinPoint join point for advice
   * @return result
   * @throws Throwable throws IllegalArgumentException
   */
  @Override
  @Around("applicationRepositoriesPointcut() && applicationServicesPointcut() && springBeanPointcut() && !execution(* toString())")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    final Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    final long start = System.currentTimeMillis();
    if (log.isDebugEnabled()) {
      log.debug("Enter: {}() with argument[s] = {}", joinPoint.getSignature().getName(),
          Arrays.toString(joinPoint.getArgs()));
    }
    try {
      Object result = joinPoint.proceed();
      if (log.isDebugEnabled()) {
        log.debug("Exit: {}() with result = {}", joinPoint.getSignature().getName(), result);
        log.debug("Metrics : {}() in {} ms.",
            joinPoint.getSignature().getName(),
            System.currentTimeMillis() - start);
      }
      return result;
    } catch (IllegalArgumentException e) {
      log.error("Illegal argument: {} in {}()", Arrays.toString(joinPoint.getArgs()),
          joinPoint.getSignature().getName());
      throw e;
    }
  }

  /**
   * Register the user in the MDC under USER_KEY.
   *
   * @param username
   * @return true if the user can be successfully registered
   */
  @Override
  public boolean registerUsername(String username) {
    if (username != null && username.trim().length() > 0) {
      MDC.put(userKey, username);
      return true;
    }
    return false;
  }

}
