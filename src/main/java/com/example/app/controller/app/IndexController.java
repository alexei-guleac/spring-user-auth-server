package com.example.app.controller.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;


@ApiIgnore
@CrossOrigin
@Controller
@ConditionalOnProperty(
    value = "swagger.enabled",
    havingValue = "true",
    matchIfMissing = false)
@Validated
@Slf4j
public class IndexController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index() {
    log.info("swagger-ui.html");
    return "redirect:swagger-ui.html?urls.primaryName=default";
  }
}

