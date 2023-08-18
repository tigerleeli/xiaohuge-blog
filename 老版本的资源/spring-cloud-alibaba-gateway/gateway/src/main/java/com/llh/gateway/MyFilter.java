package com.llh.gateway;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


/**
 * @author 小虎哥的技术博客
 */
@Component
public class MyFilter implements WebFilter{
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        // 打印请求路径，生产环境可以在此做一些权限认证等
        System.out.println(path);
        return  chain.filter(exchange);

    }
}
