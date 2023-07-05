package com.qbs.app.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.qbs.app.domain.AppUser;
import com.qbs.app.domain.requests.AuthenticationRequest;
import com.qbs.app.security.token.JwtTokenUtil;
import com.qbs.app.services.impl.AppUserService;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
  @Mock private AuthenticationManager authenticationManager;
  @Mock private AppUserService userService;
  @Mock private JwtTokenUtil tokenUtil;
  @InjectMocks private AuthController authController;

  @Test
  void testFetchUserById_success() {
    final Long userId = 1L;
    final AppUser expectedUser = AppUser.builder().Id(userId).build();
    doReturn(Optional.of(expectedUser)).when(userService).findUserById(userId);
    final Optional<AppUser> user = authController.findUserById(userId);
    assertThat(user).isNotEmpty();
    assertThat(user).isPresent();
    verify(userService).findUserById(userId);
  }

  @Test
  void testFetchUserByEmail_success() {
    final String userEmail = RandomStringUtils.randomAlphabetic(15);
    final UserDetails expectedUserDetails = mock(UserDetails.class);
    doReturn(expectedUserDetails).when(userService).loadUserByUsername(userEmail);
    final UserDetails user = authController.findUserByEmail(userEmail);
    assertThat(user).isNotNull();
    verify(userService).loadUserByUsername(userEmail);
  }

  @Test
  void testLogin_success() throws Exception {
    final String userEmail = RandomStringUtils.randomAlphabetic(15);
    final String userPassword = RandomStringUtils.randomAlphabetic(15);
    final AuthenticationRequest request =
        AuthenticationRequest.builder().email(userEmail).password(userPassword).build();
    final String token = RandomStringUtils.randomAlphabetic(20);
    final UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userEmail, userPassword);
    final UserDetails expectedUserDetails = mock(UserDetails.class);

    doReturn(expectedUserDetails).when(userService).loadUserByUsername(userEmail);
    doReturn(token).when(tokenUtil).generateToken(expectedUserDetails);

    final String loginToken = authController.login(request);

    assertThat(loginToken).isEqualTo(token);
    verify(authenticationManager).authenticate(authenticationToken);
  }

  @Test
  void testLogin_authenticationDisabledException_exceptionThrown() throws Exception {
    final AuthenticationRequest request = mock(AuthenticationRequest.class);
    final DisabledException disabledException = new DisabledException("USER_DISABLED");
    doThrow(disabledException).when(authenticationManager).authenticate(any());

    assertThrows(Exception.class, () -> authController.login(request));
    verify(userService).loadUserByUsername(any());
    verifyNoInteractions(tokenUtil);
  }

  @Test
  void testLogin_authenticationBadCredentialsException_exceptionThrown() throws Exception {
    final AuthenticationRequest request = mock(AuthenticationRequest.class);
    final BadCredentialsException badCredentialsException =
        new BadCredentialsException("INVALID_CREDENTIALS");
    doThrow(badCredentialsException).when(authenticationManager).authenticate(any());

    assertThrows(Exception.class, () -> authController.login(request));
    verify(userService).loadUserByUsername(any());
    verifyNoInteractions(tokenUtil);
  }
}
