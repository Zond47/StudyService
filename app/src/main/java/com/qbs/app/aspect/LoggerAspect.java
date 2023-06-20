package com.qbs.app.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.qbs.app.common.constants.LoggingConstants.*;
import static java.time.Duration.between;
import static java.time.Instant.now;
import static net.logstash.logback.argument.StructuredArguments.kv;

/**
 * Aspect for logging related concerns.
 */
@Aspect
@Component
@Slf4j
public class LoggerAspect {

  /**
   * Surrounds the method annotated with {@link LogExecution} in try-catch-finally block and logs
   * the action status.
   *
   * @param joinPoint the join point
   * @return the return value from the join point
   * @throws Throwable throws back any Throwable from the join point.
   */
  @Around("@annotation(com.qbs.app.aspect.LogExecution)")
  public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    boolean success = true;
    final String actionName = joinPoint.getSignature().getName();
    final String targetClass = joinPoint.getTarget().getClass().getSimpleName();
    final Instant startTime = now();
    try {
      log.info("{} {} {} ",
              kv(ACTION, actionName),
              kv(PHASE, START),
              kv(TARGET, targetClass)
      );
      return joinPoint.proceed();
    } catch (final Exception ex) {
      log.error("Caught an exception", ex);
      success = false;
      throw ex;
    } finally {
      final long duration = between(startTime, now()).toMillis();
      log.info(
              "{} {} {} {} {}",
              kv(ACTION, actionName),
              kv(PHASE, END),
              kv(TARGET, targetClass),
              kv(DURATION, duration),
              kv(SUCCESS, success)
      );
    }
  }
}
