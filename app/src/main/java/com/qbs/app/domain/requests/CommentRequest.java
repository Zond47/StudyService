package com.qbs.app.domain.requests;

import com.qbs.app.domain.enums.CommentStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class CommentRequest {
  private final BigDecimal proposedPrice;
  private final String executorId;
  private final String postId;
}
