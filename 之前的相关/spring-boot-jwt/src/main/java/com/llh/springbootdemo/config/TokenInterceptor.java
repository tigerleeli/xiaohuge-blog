package com.llh.springbootdemo.config;

import com.llh.springbootdemo.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author llh
 */
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 请求头
     */
    private static final String HEADER_AUTH = "Authorization";

    /**
     * 安全的url，不需要令牌
     */
    private static final List<String> SAFE_URL_LIST = Arrays.asList("/userInfo/login", "/userInfo/register");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        response.setContentType("application/json; charset=utf-8");

        String url = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println(url);
        // 登录和注册等请求不需要令牌
        if (SAFE_URL_LIST.contains(url)) {
            return true;
        }

        // 从请求头里面读取token
        String token = request.getHeader(HEADER_AUTH);
        if (token == null) {
            throw new RuntimeException("请求失败，令牌为空");
        }

        // 解析令牌
        Map<String, Object> map = JwtUtil.resolveToken(token);
        Long userId = Long.parseLong(map.get("userId").toString());
        ContextHolder.setUserId(userId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ContextHolder.shutdown();
    }
}
