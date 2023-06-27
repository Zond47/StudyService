package com.qbs.app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qbs.app.domain.enums.AppUserRole;
import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPost implements Serializable {

  @EmbeddedId UserPostId userPostId = new UserPostId();

  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "appUser_id")
  @JsonBackReference
  private AppUser userId;

  @ManyToOne
  @MapsId("postId")
  @JoinColumn(name = "post_id")
  @JsonBackReference
  private Post postId;

  private AppUserRole role;
}
