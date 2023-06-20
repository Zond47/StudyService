package com.qbs.app.security.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter { //extends OncePerRequestFilter {
  // TODO: Implement filtering by Authorization header
/*  private final AppUserService userService;
  private final JwtTokenUtil tokenUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

  }*/
}
