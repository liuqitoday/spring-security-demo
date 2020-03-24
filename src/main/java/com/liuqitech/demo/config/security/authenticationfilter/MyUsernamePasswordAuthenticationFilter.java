package com.liuqitech.demo.config.security.authenticationfilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuqitech.demo.config.security.constants.LoginUrlConstants;
import com.liuqitech.demo.model.dto.UsernamePasswordLoginParam;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class MyUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private boolean postOnly = true;

  public MyUsernamePasswordAuthenticationFilter() {
    super(new AntPathRequestMatcher(LoginUrlConstants.LOGIN_URL_USERNAME_PASSWORD,
        HttpMethod.POST.name()));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    if (postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    }
    String username = "";
    String password = "";
    ObjectMapper mapper = new ObjectMapper();
    try (ServletInputStream inputStream = request.getInputStream()) {
      UsernamePasswordLoginParam loginParam = mapper
          .readValue(inputStream, UsernamePasswordLoginParam.class);
      username = loginParam.getUsername();
      password = loginParam.getPassword();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (username == null) {
      username = "";
    }
    if (password == null) {
      password = "";
    }
    username = username.trim();
    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
        username, password);
    return this.getAuthenticationManager().authenticate(authRequest);
  }

}
