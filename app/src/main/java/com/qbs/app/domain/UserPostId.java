package com.qbs.app.domain;

import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Builder
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostId implements Serializable {
  @Column(name = "appUser_id")
  private Long userId;

  @Column(name = "post_id")
  private Long postId;
}
