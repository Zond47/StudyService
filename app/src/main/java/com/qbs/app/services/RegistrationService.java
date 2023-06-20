package com.qbs.app.services;

import com.qbs.app.aspect.LogExecution;
import com.qbs.app.model.requests.RegistrationRequest;

public interface RegistrationService {
  @LogExecution
  String register(final RegistrationRequest request);

  @LogExecution
  void confirmToken(final String token);
}
