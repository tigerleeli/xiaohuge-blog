package com.llh.springbootdemo.utils;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * @author llh
 */
public class JwtUtil {
    /**
     * 令牌密码 不少于32位
     */
    private static final String SECRET = "token_secret";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "Bearer";

    /**
     * 令牌过期时间
     */
    private static final Integer EXPIRE_SECONDS = 60 * 60 * 24 * 7;


    /**
     * 生成令牌
     */
    public static String generateToken(Map<String, Object> map) {
        String jwt = Jwts.builder()
                .setSubject("user info").setClaims(map)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setExpiration(DateUtil.offsetSecond(new Date(), EXPIRE_SECONDS))
                .compact();
        return TOKEN_PREFIX + "_" + jwt;
    }

    /**
     * 验证令牌
     */
    public static Map<String, Object> resolveToken(String token) {
        if (token == null) {
            throw new RuntimeException("令牌为空");
        }
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replaceFirst(TOKEN_PREFIX + "_", ""))
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("令牌已过期");
        } catch (Exception e) {
            throw new RuntimeException("令牌解析异常");
        }
    }

}