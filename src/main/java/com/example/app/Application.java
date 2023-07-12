package com.example.app;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  private static final String SERVER_PORT = "server.port";

  private static final String SERVER_SSL_ENABLED = "server.ssl.enabled";

  private static final String HTTP_PROXY_HOST = "http.proxyHost";

  private static final String HTTP_PROXY_PORT = "http.proxyPort";

  private static final String APP_BE_VERSION = "app.be.version";

  public static void main(String[] args) throws Exception {
    SpringApplication app = new SpringApplication(Application.class);
    ApplicationContext context = app.run(args);
    Environment env = context.getEnvironment();

    String version = env.getProperty(APP_BE_VERSION, "update");
    log.info("version: " + version);

    String port = env.getProperty(SERVER_PORT, "8181");
    String schema =
        env.getProperty(SERVER_SSL_ENABLED, Boolean.class, Boolean.FALSE) ? "https" : "http";

    // log.info("detecting proxies");
    List<Proxy> list = null;
    try {
      list = ProxySelector.getDefault().select(new URI("https://google.com"));
    } catch (URISyntaxException e) {
      log.error("URISyntaxException ", e);
    }
    if (list != null) {
      for (Proxy proxy : list) {
        log.info("proxy type: " + proxy.type());

        InetSocketAddress addr = (InetSocketAddress) proxy.address();

        if (addr == null) {
          log.info("No Proxy");
        } else {
          log.info("proxy hostname: " + addr.getHostName());

          System.setProperty(HTTP_PROXY_HOST, addr.getHostName());
          log.info("proxy port: " + addr.getPort());
          System.setProperty(HTTP_PROXY_PORT, Integer.toString(addr.getPort()));
        }
      }
    }

    log.info("Local: \t\t{}://127.0.0.1:{}", schema, port);
    log.info("External: \t{}://{}:{}", schema, InetAddress.getLocalHost().getHostAddress(), port);
  }

}
