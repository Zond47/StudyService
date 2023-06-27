package com.qbs.app.controllers;

import com.qbs.app.domain.AppUser;
import com.qbs.app.domain.requests.AuthenticationRequest;
import com.qbs.app.security.token.JwtTokenUtil;
import com.qbs.app.services.impl.AppUserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/login")
@Slf4j
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final AppUserService userService;
  private final JwtTokenUtil jwtTokenUtil;

  @PostMapping
  public String login(@RequestBody final AuthenticationRequest request) throws Exception {
    log.info("Attempt to login.");
    final UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
    log.info("User exists, creating token.");
    authenticate(request.getEmail(), request.getPassword());
    return jwtTokenUtil.generateToken(userDetails);
  }

  @GetMapping("/userDetails")
  public UserDetails fetchUserByEmail(@RequestParam("email") final String email) {
    return userService.loadUserByUsername(email);
  }

  @GetMapping("/id")
  public Optional<AppUser> fetchUserById(@PathVariable("id") final Long Id) {
    return userService.findUserById(Id);
  }

  private void authenticate(final String username, final String password) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
    } catch (final DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (final BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}
