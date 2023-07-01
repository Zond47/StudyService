package com.qbs.app.domain.requests;

import com.qbs.app.domain.enums.CommentStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentRequest {
  private BigDecimal proposedPrice;
}
