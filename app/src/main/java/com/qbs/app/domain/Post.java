package com.qbs.app.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.qbs.app.domain.enums.PostStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Getter
@Setter
public class Post {

  @Id
  @SequenceGenerator(name = "post_sequence",
          sequenceName = "posts_sequence",
          allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
          generator = "posts_sequence")
  private Long Id;
  private String customerId;
  private String executorId;
  @Enumerated(EnumType.STRING)
  private PostStatus status;
  private LocalDateTime serviceDate;
  private String jobTags;
  private boolean isFinalPropose;
  private String serviceAddress;
  private BigDecimal proposedPrice;

  @OneToMany(mappedBy="post",
          cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
          fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Comment> commentList;

  public Post(final String customerId,
              final PostStatus status,
              final LocalDateTime serviceDate,
              final String jobTags,
              final boolean isFinalPropose,
              final String serviceAddress,
              final BigDecimal proposedPrice) {
    this.customerId = customerId;
    this.executorId = null;
    this.status = status;
    this.serviceDate = serviceDate;
    this.jobTags = jobTags;
    this.isFinalPropose = isFinalPropose;
    this.serviceAddress = serviceAddress;
    this.proposedPrice = proposedPrice;
    this.commentList = null;
  }

  public void addComment(Comment comment) {
    if (!getCommentList().contains(comment)) {
      getCommentList().add(comment);
      if (comment.getPost() != null) {
        comment.getPost().getCommentList().remove(comment);
      }
      comment.setPost(this);
    }
  }

}
