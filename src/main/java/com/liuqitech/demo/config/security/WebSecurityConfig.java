package com.liuqitech.demo.config.security;

import com.liuqitech.demo.config.security.authenticationprovider.UsernamePasswordAuthenticationProvider;
import com.liuqitech.demo.config.security.constants.LoginUrlConstants;
import com.liuqitech.demo.config.security.authenticationfilter.MyUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  @Autowired
  private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

  @Autowired
  private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

  @Autowired
  private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Bean
  public UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider() {
    return new UsernamePasswordAuthenticationProvider();
  }

  public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter(
      AuthenticationManager authenticationManager) {
    MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter
        = new MyUsernamePasswordAuthenticationFilter();
    myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager);
    myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(
        customAuthenticationFailureHandler);
    myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(
        customAuthenticationSuccessHandler);
    return myUsernamePasswordAuthenticationFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable() // 关闭csrf
        .authorizeRequests() // 启用访问限制
        .antMatchers(LoginUrlConstants.LOGIN_URL_USERNAME_PASSWORD).permitAll()
        .anyRequest().authenticated() // 任何请求都需要认证
        .and()
        .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);

    http
        .addFilterBefore(myUsernamePasswordAuthenticationFilter(authenticationManager()),
            UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .authenticationProvider(usernamePasswordAuthenticationProvider);
  }

}
