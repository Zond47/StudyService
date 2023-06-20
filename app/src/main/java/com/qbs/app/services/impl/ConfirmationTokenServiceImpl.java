package com.qbs.app.services.impl;

import com.qbs.app.repositories.ConfirmationTokenRepository;
import com.qbs.app.security.token.ConfirmationToken;
import com.qbs.app.services.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
  private final ConfirmationTokenRepository confirmationTokenRepository;

  public void saveConfirmationToken(final ConfirmationToken token) {
    confirmationTokenRepository.save(token);
  }

  public Optional<ConfirmationToken> findConfirmationToken(final String token) {
    return confirmationTokenRepository.findByToken(token);
  }

  public int setConfirmedAt(final String token) {
    return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
  }
}
