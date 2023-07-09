package com.qbs.app.domain.requests;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRequest {
  private String customerId;
  private LocalDateTime serviceDate;
  private String jobTags;
  private boolean isFinalPropose;
  private String serviceAddress;
  private BigDecimal proposedPrice;
}
