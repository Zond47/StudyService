package com.qbs.app.services.impl;

import com.qbs.app.model.AppUser;
import com.qbs.app.model.enums.AppUserRole;
import com.qbs.app.model.requests.RegistrationRequest;
import com.qbs.app.security.token.ConfirmationToken;
import com.qbs.app.services.ConfirmationTokenService;
import com.qbs.app.services.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@AllArgsConstructor
@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

  private final AppUserService userService;
  private final ConfirmationTokenService confirmationTokenService;

  public String register(final RegistrationRequest request) {
    if (request.getFirstName().isEmpty()
            || request.getLastName().isEmpty()
            || request.getEmail().isEmpty()
            || request.getPassword().isEmpty()) {
      throw new IllegalStateException("Input fields cannot be empty!");
    }
    return userService.signUpUser(
            new AppUser(request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPassword(),
                    AppUserRole.STUDENT
            ));
  }

  @Transactional
  public void confirmToken(final String token) {
    final ConfirmationToken confirmationToken =
            confirmationTokenService
                    .findConfirmationToken(token)
                    .orElseThrow(() -> new IllegalStateException("Token not found!"));
    if (confirmationToken.getConfirmedAt() != null) {
      throw new IllegalStateException("Email already confirmed");
    }

    final LocalDateTime expiredAt = confirmationToken.getExpiresAt();

    if (expiredAt.isBefore(LocalDateTime.now())) {
      throw new IllegalStateException("Token expired!");
    }

    confirmationTokenService.setConfirmedAt(token);
    userService.enableAppUser(confirmationToken.getAppUser().getEmail());
    log.info("Token confirmed! Account is enabled!");
  }

}
