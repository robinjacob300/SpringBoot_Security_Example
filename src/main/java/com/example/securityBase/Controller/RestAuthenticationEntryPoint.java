package com.example.securityBase.Controller;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RestControllerAdvice
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException ) throws IOException, ServletException {    
    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + "\" }");
  }

  @ExceptionHandler(value = { AccessDeniedException.class })
  public void commence(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex ) throws IOException {
    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getOutputStream().println("{ \"error\": \"" + ex.getMessage() + "\" }");
    ex.printStackTrace();
  }
}