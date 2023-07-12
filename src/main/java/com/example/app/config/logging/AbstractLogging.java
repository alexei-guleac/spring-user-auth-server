package com.example.app.config.logging;

import java.util.Arrays;
import org.springframework.core.env.Environment;


abstract class AbstractLogging {

  protected static final Integer stackMaxLines = 15;
  protected static final String MAIN_APP_PACKAGE = "com.example.app";
  // a "new line" string works on any operating system
  final String nl = System.getProperty("line.separator");
  Environment env;

  String generateTitle(String title) {
    return String.format(
        "%s---------------------------------------%s---------------------------------------",
        nl, title
    );
  }

  Throwable stackTraceRetainOnlyPackageClasses(Throwable e, String filterPackage) {
    e.setStackTrace(
        Arrays.stream(e.getStackTrace())
            .filter(se -> se.getClassName().startsWith(filterPackage))
            .toArray(StackTraceElement[]::new));
    //either e.printStacktrace();
    //or rethrow
    return e;
  }

  Throwable stackTraceRetainOnlyAppClasses(Throwable e) {
    return stackTraceRetainOnlyPackageClasses(e, MAIN_APP_PACKAGE);
  }
}
