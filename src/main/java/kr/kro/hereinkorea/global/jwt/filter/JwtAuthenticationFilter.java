package kr.kro.hereinkorea.global.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kro.hereinkorea.global.jwt.properties.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = null;
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authorization) && authorization.startsWith("Bearer")){
            jwt = authorization.substring(7);
        }
        if(jwt != null){
            SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(jwt));
        }

        filterChain.doFilter(request,response);

    }
}
