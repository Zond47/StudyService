package com.qbs.app.services.impl;

import com.qbs.app.aspect.LogExecution;
import com.qbs.app.model.AppUser;
import com.qbs.app.repositories.AppUserRepository;
import com.qbs.app.security.token.ConfirmationToken;
import com.qbs.app.services.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@AllArgsConstructor
@Service
@Slf4j
public class AppUserService implements UserDetailsService {

  private static final String USER_NOT_FOUND_MSG = "User with email %s not found";
  private final AppUserRepository appUserRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final ConfirmationTokenService confirmationTokenService;

  @Override
  public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
    return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
  }

  @LogExecution
  public String signUpUser(final AppUser appUser) {
    log.info("Attempt to signUp user.");
    final Optional<AppUser> user = appUserRepository.findByEmail(appUser.getEmail());
    if (user.isPresent()) {
      throw new IllegalStateException("Email already registered!");
    }
    final String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
    appUser.setPassword(encodedPassword);

    appUserRepository.save(appUser);

    final String token = UUID.randomUUID().toString();
    final ConfirmationToken confirmationToken = new ConfirmationToken(token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            appUser);
    confirmationTokenService.saveConfirmationToken(confirmationToken);
    log.info("Token has been created. {}", kv("token", token));
    // TODO: Send EMAIL

    return token;
  }

  public void enableAppUser(final String email) {
    appUserRepository.enableAppUser(email);
  }

  public List<AppUser> findAllUsers() {
    return appUserRepository.findAll();
  }

}
