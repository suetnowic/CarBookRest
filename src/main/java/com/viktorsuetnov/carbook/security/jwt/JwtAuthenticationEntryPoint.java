package com.viktorsuetnov.carbook.security.jwt;

import com.google.gson.Gson;
import com.viktorsuetnov.carbook.payload.response.InvalidLoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Value("${carbook.app.headerString}")
    private String headerString;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        InvalidLoginResponse loginResponse = new InvalidLoginResponse();
        String jsonLoginResponse = new Gson().toJson(loginResponse);
        response.setContentType(headerString);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().println(jsonLoginResponse);
    }
}
