package com.zzwei.server.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken工具类
 */
@Component
public class JwtTokenUtil {

    //定义用户名和过期时间
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    @Value("${jwt.secret}")
    //jwt加解密是用的密钥
    private String secret;
    @Value("${jwt.expiration}")
    //jwt的超期限时间 （60*60*24） 24小时失效
    private Long expiration;

    //可供外界调用的方法
    //1.根据用户信息生产token
    //2.从token中获取登录用户名
    //3.验证token是否有效
    //4.判断token是否可以被刷新
    //5.刷新token

    /**
     * 1.根据用户信息生成token
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        //准备存放token的容器(荷载)
        Map<String, Object> claims = new HashMap<>();
        //从security框架UserDetails中获取用户名
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        //创建时间为当前时间
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 4.从token中获取登录用户名
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        //从token中获取荷载
        try {
            Claims claims = getClaimsFormToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 6.验证token是否有效
     *
     * @param token
     * @param userDetails
     * @return
     */
    public boolean vaildateToken(String token, UserDetails userDetails) {
        return getUsernameFromToken(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 7.判断token是否失效
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        //获取token失效时间
        Date expirDate = getExpireDateFromToken(token);
        //如果过期时间在当前时间之前，有效
        return expirDate.before(new Date());
    }

    /**
     * 8.从token中获取过期时间
     *
     * @param token
     * @return
     */
    private Date getExpireDateFromToken(String token) {
        //根据token获取荷载
        Claims claims = getClaimsFormToken(token);
        //从荷载中获取过期时间
        return claims.getExpiration();
    }

    /**
     * 9.判断token是否可以被刷新
     *
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 10.刷新token过期时间
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        //获取荷载
        Claims claims = getClaimsFormToken(token);
        //通过荷载设置创建时间改为当前时间
        claims.put(CLAIM_KEY_CREATED, new Date());
        //生成token
        return generateToken(claims);
    }

    /**
     * 5.从token获取荷载
     *
     * @param token
     * @return
     */
    private Claims getClaimsFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 2.根据荷载生成JWT token
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 3.生成token失效时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


}
