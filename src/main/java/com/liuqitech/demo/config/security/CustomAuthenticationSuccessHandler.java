package com.liuqitech.demo.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
      HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.setHeader("Content-Type", "application/json;charset=UTF-8");
    response.getWriter().write("{\"msg\":\"hi\"}");
  }
}
