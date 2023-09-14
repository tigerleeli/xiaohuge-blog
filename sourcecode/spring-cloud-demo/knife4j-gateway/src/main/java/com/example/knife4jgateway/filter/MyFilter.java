package com.example.knife4jgateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class MyFilter implements WebFilter {
    private final List<String> excludePatterns = Arrays.asList("/doc.html", "/webjars/**", "/v3/api-docs/**");

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        // 打印请求路径，生产环境可以在此做一些权限认证等
        System.out.println(path);

        // 判断是否排除
        boolean isExcluded = excludePatterns.stream().anyMatch(o -> pathMatcher.match(o, path));

        return chain.filter(exchange);
    }
}
