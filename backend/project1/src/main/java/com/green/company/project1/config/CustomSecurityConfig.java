package com.green.company.project1.config;

import com.green.company.project1.security.filter.JWTCheckFilter;
import com.green.company.project1.security.handler.APILoginFailureHandler;
import com.green.company.project1.security.handler.APILoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CustomSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("**Security Config Starter**");
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
        });
        http.sessionManagement(i->i.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(i->i.disable());
        http.formLogin(i-> {
            i.loginPage("/api/member/login");

            i.successHandler(new APILoginSuccessHandler());
            i.failureHandler(new APILoginFailureHandler());
        });
        http.addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class);//JWT 체크
        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        log.info("-------CorsConfiguration execution---------");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){ // 단방향 암호, 비밀번호 -> 암호화 하여 데이터 베이스에 저장
        return new BCryptPasswordEncoder();
        //암호화된 문자열을 복호 원래 비밀번호 복원 안됨
    }
}

