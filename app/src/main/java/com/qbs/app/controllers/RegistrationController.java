package com.qbs.app.controllers;

import com.qbs.app.domain.requests.RegistrationRequest;
import com.qbs.app.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
  private final RegistrationService registrationService;

  @PostMapping
  public String register(@RequestBody final RegistrationRequest request) {
    return registrationService.register(request);
  }

  @GetMapping("/confirm")
  public void confirm(@RequestParam("token") final String token) {
    registrationService.confirmToken(token);
  }
}
