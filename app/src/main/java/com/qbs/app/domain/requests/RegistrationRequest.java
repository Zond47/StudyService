package com.qbs.app.domain.requests;

import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistrationRequest {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
}
