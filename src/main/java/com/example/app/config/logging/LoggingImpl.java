package com.example.app.config.logging;

import com.example.app.config.ProfileConstants;
import com.example.app.model.data.healthcheck.HealthCheckResponseData;
import com.example.app.service.UserSecurityContextService;
import com.example.app.util.DateUtils;
import com.example.app.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Profile({
    ProfileConstants.SPRING_PROFILE_DEVELOPMENT,
    ProfileConstants.SPRING_PROFILE_TEST
})
@Slf4j
public class LoggingImpl extends LoggingBaseImpl {

  public LoggingImpl(Environment env,
      UserSecurityContextService userSecurityContextService,
      DateUtils dateUtils, com.example.app.util.StringUtils stringUtils, JwtUtils jwtUtils) {
    super(env, userSecurityContextService, dateUtils, stringUtils, jwtUtils);
  }

  @Override
  @AfterReturning(pointcut = "controllerExecutionLogger()", returning = "response")
  public void afterReturningControllerAdvice(
      JoinPoint joinPoint, ResponseEntity response
  ) {
//    log.info(String.valueOf(response));
    if (response != null) {
      String responseBody = String.valueOf(response.getBody());
      if (!(responseBody.contains(HealthCheckResponseData.class.getSimpleName()) && responseBody
          .contains(("status=OK")))) {
        log.info(
            this.generateTitle("Response")
                + nl + "Response status: " + response.getStatusCodeValue()
                + nl + "Response body: " + responseBody + nl
        );
      }
    }
  }
}
