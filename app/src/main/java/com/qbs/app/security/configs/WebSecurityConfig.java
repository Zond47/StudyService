package com.qbs.app.security.configs;

import com.qbs.app.services.impl.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

  private final AppUserService userService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Bean
  public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((authorize) -> authorize
                    .anyRequest().permitAll()
            );
    http.csrf().disable();
    return http.build();
        /* http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v* /registration/ * *")
                .permitAll()
                .antMatchers("/h2-console/")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin();
        return http.build(); */
  }

  @Bean
  public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder
            .userDetailsService(userService)
            .passwordEncoder(bCryptPasswordEncoder);
    return authenticationManagerBuilder.build();
  }

}
