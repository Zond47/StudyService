package com.qbs.app.domain;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Embeddable;
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
