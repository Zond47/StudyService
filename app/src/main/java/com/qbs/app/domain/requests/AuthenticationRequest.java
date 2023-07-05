package com.qbs.app.domain.requests;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class AuthenticationRequest {
  private final String email;
  private final String password;
}
