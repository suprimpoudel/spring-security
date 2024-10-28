package com.np.suprimpoudel.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.np.suprimpoudel.dto.Base;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAuthenticationFailedHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        var base = new Base<String>();
        base.setData(authException.getMessage());

        OutputStream out = response.getOutputStream();
        objectMapper.writeValue(out, base);
        out.flush();
    }
}