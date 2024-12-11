package kr.kro.hereinkorea.global.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        }catch (JwtException e)
        {
            setResponse(request,response,e.getMessage());
        }
    }

    private void setResponse(HttpServletRequest request, HttpServletResponse response, String message)
        throws IOException{
            Map<String, Object> body=new HashMap<>();
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
            body.put("status",HttpServletResponse.SC_UNAUTHORIZED);
            body.put("message",message);
            body.put("path",request.getServletPath());
            ObjectMapper mapper=new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), body);
        }
}
