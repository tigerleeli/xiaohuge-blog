package com.llh.moneykeep.common;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * 令牌工具
 */
public class JwtUtil {
    /**
     * 令牌密码 不少于32位
     */
    public static final String SECRET = "adsffdasadsf;;;j";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer";

    /**
     * 令牌过期时间 7天
     */
    public static final Integer TOKEN_EXPIRE_SECONDS = 7 * 24 * 60 * 60;

    /**
     * 生成令牌
     */
    public static String generateToken(Map<String, Object> map) {
        String jwt = Jwts.builder()
                .setSubject("user info").setClaims(map)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setExpiration(DateUtil.offsetSecond(new Date(), TOKEN_EXPIRE_SECONDS))
                .compact();
        return TOKEN_PREFIX + "_" + jwt;
    }

    /**
     * 解析令牌
     */
    public static Map<String, Object> resolveToken(String token) {
        if (token == null) {
            throw new BizException("令牌为空");
        }
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX + "_", ""))
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new BizException("令牌已过期");
        } catch (Exception e) {
            throw new BizException("令牌解析异常");
        }
    }
}
