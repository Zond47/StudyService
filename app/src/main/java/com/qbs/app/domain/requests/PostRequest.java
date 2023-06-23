package com.qbs.app.domain.requests;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class PostRequest {
  private final String customerId;
  private final LocalDateTime serviceDate;
  private final String jobTags;
  private boolean isFinalPropose;
  private String serviceAddress;
  private BigDecimal proposedPrice;
}
