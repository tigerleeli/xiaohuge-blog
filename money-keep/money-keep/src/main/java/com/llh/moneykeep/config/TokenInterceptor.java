package com.llh.moneykeep.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import com.llh.moneykeep.common.CommonResult;
import com.llh.moneykeep.common.ContextHolder;
import com.llh.moneykeep.common.ContextObject;
import com.llh.moneykeep.common.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 令牌拦截器
 **/
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 不需要令牌的接口
     */
    public static final List<String> NO_TOKEN_URL_LIST = Arrays.asList("/user/login", "/user/register");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String clientIp = ServletUtil.getClientIP(request);
        String url = request.getRequestURI().substring(request.getContextPath().length());
        log.info("客户端ip: {}  接口: {} ", clientIp, url);

        // 不需要令牌的请求接口
        boolean isNoTokenUrl = NO_TOKEN_URL_LIST.stream().anyMatch(o -> pathMatcher.match(o, url));
        if (isNoTokenUrl) {
            return true;
        }

        String token = request.getHeader("Authorization");
        if (StrUtil.isBlank(token)) {
            log.info("缺少令牌，请先登录");
            return handleResult(response, CommonResult.error(20001, "请先登录"));
        }

        try {
            Map<String, Object> map = JwtUtil.resolveToken(token);
            String userId = (String) map.get("userId");
            // 放入上下文
            ContextHolder.setContext(new ContextObject(Long.parseLong(userId)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return handleResult(response, CommonResult.error(20002, "令牌解析出错"));
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContextHolder.removeContext();
    }

    private boolean handleResult(HttpServletResponse response, CommonResult<String> responseResult) {
        try {
            response.setContentType("application/json; charset=utf-8");
            OutputStream stream = response.getOutputStream();
            byte[] bytes = JSONUtil.toJsonStr(responseResult).getBytes(StandardCharsets.UTF_8);
            stream.write(bytes);
            stream.flush();
            stream.close();
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
