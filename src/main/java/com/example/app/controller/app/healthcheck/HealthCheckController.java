package com.example.app.controller.app.healthcheck;

import static com.example.app.constants.RequestMappings.HEALTH_CHECK_ROOT;

import com.example.app.api.HealthCheckApi;
import com.example.app.model.data.healthcheck.HealthCheckResponseData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(HEALTH_CHECK_ROOT)
public class HealthCheckController implements HealthCheckApi {

  private static final String STATUS = "OK";
  @Value("${info.app}")
  private String appName;

  @Override
  public HealthCheckResponseData healthCheck() {
    return HealthCheckResponseData.builder()
        .name(appName)
        .status(STATUS)
        .build();
  }
}
