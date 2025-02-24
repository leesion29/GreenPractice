package com.green.company.project1.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JWTUtil {
    private static String key = "1234567890123456789012345678901234567890"; // 임의의 숫자를 넣는다. 길수록 암호화 정도가 강화된다
    public static String generateToken(Map<String, Object> valueMap, int min){
        SecretKey key = null;
        try{
            key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));
        } catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
        String jwtStr = Jwts.builder()
                .setHeader(Map.of("type", "JWT"))
                .setClaims(valueMap)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key)
                .compact();
        return jwtStr;
    }
    public static Map<String, Object> validateToken(String token) {
        Map<String, Object> claim = null;
        try{
            claim = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token).getBody();
        } catch (MalformedJwtException malformedJwtException) {
            throw new CustomJWTException("Malformed");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new CustomJWTException("expired");
        } catch (InvalidClaimException invalidClaimException) {
            throw new CustomJWTException("Invalid");

        } catch (JwtException jwtException) {
            throw new CustomJWTException("JWTError");
        } catch (Exception e) {
            throw new CustomJWTException("Error");
        }
        return claim;
    }
}
