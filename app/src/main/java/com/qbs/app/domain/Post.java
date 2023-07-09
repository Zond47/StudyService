package com.qbs.app.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.qbs.app.domain.enums.PostStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

import jakarta.persistence.SequenceGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Getter
@Setter
public class Post {

  @Id
  @SequenceGenerator(name = "post_sequence", sequenceName = "posts_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_sequence")
  private Long Id;

  @OneToMany(
      mappedBy = "postId",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
      fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<UserPost> users;

  @Enumerated(EnumType.STRING)
  private PostStatus status;

  private LocalDateTime serviceDate;
  private String jobTags;
  private boolean isFinalPropose;
  private String serviceAddress;
  private BigDecimal proposedPrice;

  @OneToMany(
      mappedBy = "post",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
      fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Comment> commentList;

  public Post(
      final PostStatus status,
      final LocalDateTime serviceDate,
      final String jobTags,
      final boolean isFinalPropose,
      final String serviceAddress,
      final BigDecimal proposedPrice) {
    users = new ArrayList<>();
    this.status = status;
    this.serviceDate = serviceDate;
    this.jobTags = jobTags;
    this.isFinalPropose = isFinalPropose;
    this.serviceAddress = serviceAddress;
    this.proposedPrice = proposedPrice;
    this.commentList = null;
  }
}
