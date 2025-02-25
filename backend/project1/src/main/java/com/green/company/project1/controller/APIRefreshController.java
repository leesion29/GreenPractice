package com.green.company.project1.controller;

import com.green.company.project1.util.CustomJWTException;
import com.green.company.project1.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class APIRefreshController {
    @RequestMapping("/api/member/refresh")
    public Map<String, Object> refresh(@RequestHeader("Authorization") String authHeader, String refreshToken){
        if(refreshToken == null) throw new CustomJWTException("NULL_REFRESH");
        if(authHeader == null || authHeader.length()<7) throw new CustomJWTException("INVALID_STRING");
        String accessToken = authHeader.substring(7);
        // 액세스 토큰이 만료되지 않았다면
        if(!checkExpiredToken(accessToken)) return  Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    //refresh 토큰 검증
    Map<String, Object> claims = JWTUtil.validateToken(refreshToken);
    log.info("refresh... claims : " + claims);
    String newRefreshToken =
            checkTime((Integer)claims.get("exp"))==true?JWTUtil.generateToken(claims, 60*24):refreshToken;
        return  Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    }
    private boolean checkTime(Integer exp) {
        // JWT exp를 날짜로 변환
        Date expDate = new Date((long)exp * (1000));
        //현재 시간과의 차이 계산 -> 밀리 세컨드
        long gap = expDate.getTime() -System.currentTimeMillis();
        //분 단위 계산
        long leftMin = gap/(1000*60);
        //1 시간도 안 남았는가?
        return leftMin<60;
    }

    private boolean checkExpiredToken(String token) {
        try {
            JWTUtil.validateToken(token);
        } catch (CustomJWTException ex) {
            if(ex.getMessage().equals("Expired")) return true;
        }
        return false;
    }
}
