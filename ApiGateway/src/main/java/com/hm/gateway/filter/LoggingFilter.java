package com.hm.gateway.filter;


import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import reactor.core.publisher.Mono;

/**
 * {@code LoggingFilter} is a global filter for Spring Cloud Gateway
 * that logs every incoming HTTP request's method and URL.
 *
 * <p>This filter executes early in the filter chain (due to {@code getOrder() returning -1})
 * and helps in monitoring and debugging by providing insight into the request traffic
 * passing through the API Gateway.</p>
 *
 * <p>It uses Log4j2 for logging and is automatically registered as a Spring Bean
 * due to the {@code @Component} annotation.</p>
 */
@Component
@Log4j2
public class LoggingFilter implements GlobalFilter, Ordered {

  /**
   * Intercepts all incoming requests through the Gateway and logs their method and URL.
   *
   * @param exchange the current server exchange containing the HTTP request and response
   * @param chain    the gateway filter chain to delegate the request to
   * @return a {@link Mono} that indicates when request processing is complete
   */
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    final String method = exchange.getRequest().getMethod().toString();
    final String path = exchange.getRequest().getURI().toString();

    log.info("Incoming Request - Method: {}, URL: {}", method, path);

    return chain.filter(exchange);
  }

  /**
   * Specifies the order in which this filter should be executed.
   * Lower values have higher precedence.
   *
   * @return the order value; -1 ensures this filter runs early in the chain
   */
  @Override
  public int getOrder() {
    return -1;
  }
}


