package com.qbs.app.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;import static org.mockito.Mockito.*;

import com.qbs.app.domain.requests.RegistrationRequest;
import com.qbs.app.services.RegistrationService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {
  @Mock private RegistrationService registrationService;
  @InjectMocks private RegistrationController registrationController;

  @Test
  void testRegister_success() {
    final RegistrationRequest request = mock(RegistrationRequest.class);
    final String expectedToken = RandomStringUtils.randomAlphabetic(15);
    doReturn(expectedToken).when(registrationService).register(request);

    final String registerToken = registrationController.register(request);

    assertThat(registerToken).isEqualTo(expectedToken);
    verify(registrationService).register(request);
  }

  @Test
  void testConfirmToken_success() {
    final String token = RandomStringUtils.randomAlphabetic(15);
    
    registrationController.confirm(token);

    verify(registrationService).confirmToken(token);
  }
}
