package com.qbs.app.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.qbs.app.domain.enums.CommentStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class Comment {

  @Id
  @SequenceGenerator(name = "comment_sequence",
          sequenceName = "comments_sequence",
          allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "comments_sequence")
  private Long Id;

  @ManyToOne
  @JoinColumn(name="post_id", nullable=false)
  @JsonBackReference
  private Post post;

  @ManyToOne
  @JoinColumn(name="appUser_id", nullable=false)
  @JsonBackReference
  private AppUser executor;
  private BigDecimal proposedPrice;
  @Enumerated(EnumType.STRING)
  private CommentStatus commentStatus;

  public Comment(final Post post,
                 final AppUser executor,
                 final BigDecimal proposedPrice,
                 final CommentStatus commentStatus) {
    this.post = post;
    this.executor = executor;
    this.proposedPrice = proposedPrice;
    this.commentStatus = commentStatus;
  }
}
