package com.qbs.app.domain.requests;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class RegistrationRequest {
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String password;
}
