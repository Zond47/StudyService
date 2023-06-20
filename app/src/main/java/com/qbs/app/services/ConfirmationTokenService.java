package com.qbs.app.services;

import com.qbs.app.aspect.LogExecution;
import com.qbs.app.security.token.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {
  @LogExecution
  void saveConfirmationToken(final ConfirmationToken token);

  @LogExecution
  Optional<ConfirmationToken> findConfirmationToken(final String token);

  @LogExecution
  int setConfirmedAt(final String token);
}
