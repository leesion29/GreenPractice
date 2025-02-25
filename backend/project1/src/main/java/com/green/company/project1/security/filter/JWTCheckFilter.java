package com.green.company.project1.security.filter;

import com.google.gson.Gson;
import com.green.company.project1.dto.MemberDTO;
import com.green.company.project1.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class JWTCheckFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("------필터통과-------");
        String authHeaderStr = request.getHeader("Authorization");
        try{
            String accessToken = authHeaderStr.substring(7);
            Map<String,Object> claims = JWTUtil.validateToken(accessToken);
            logger.info("JWT claims: " + claims);
            String email = (String) claims.get("email");
            String pw = (String) claims.get("pw");
            String nickname = (String) claims.get("nickname");
            Boolean social = (Boolean) claims.get("social");
            List<String> roleNames = (List<String>) claims.get("roleNames");
            MemberDTO dto = new MemberDTO(email, pw, nickname, social.booleanValue(), roleNames);
            logger.info("========dto========" + dto);
            logger.info(dto.getAuthorities());
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto, pw, dto.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("JWT Check Error...");
            logger.error(e.getMessage());
            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "Error_Access_Token"));
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(msg);
            out.close();
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path= request.getRequestURI();
        logger.info("check uri.........여기는 fitler를 통과하지 않아도 되는 것을 설정함" +path);
        if(request.getMethod().equals("OPTIONS")) return true;
        //api/member/경로의 호출은 체크하지 않음
        if(path.startsWith("/api/member")) return true;
        //이미지 조회 경로는 체크하지 않음
        if(path.startsWith("/api/products/view")) return  true;
        return false;
    }
}
