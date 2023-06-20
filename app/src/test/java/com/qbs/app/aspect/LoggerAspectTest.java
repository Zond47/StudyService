package com.qbs.app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoggerAspectTest {
  private final LoggerAspect loggerAspect = new LoggerAspect();
  public static final ProceedingJoinPoint PROCEEDING_JOIN_POINT = mock(ProceedingJoinPoint.class);
  private static final Signature SIGNATURE = mock(Signature.class);
  private static final Object TARGET = mock(Object.class);
  private static final String ACTION_NAME = "test-method-join-point";
  public static final String RETURN_VALUE = "test-return-value";

  @Test
  void testLoggerAspect_methodSuccessfullyLogged() throws Throwable {
    doReturn(SIGNATURE).when(PROCEEDING_JOIN_POINT).getSignature();
    doReturn(ACTION_NAME).when(SIGNATURE).getName();
    doReturn(TARGET).when(PROCEEDING_JOIN_POINT).getTarget();
    doReturn(RETURN_VALUE).when(PROCEEDING_JOIN_POINT).proceed();

    assertThat(loggerAspect.logMethod(PROCEEDING_JOIN_POINT)).isEqualTo(RETURN_VALUE);
  }

  @Test
  void testLoggerAspect_methodThrowsException() throws Throwable {
    doReturn(SIGNATURE).when(PROCEEDING_JOIN_POINT).getSignature();
    doReturn(ACTION_NAME).when(SIGNATURE).getName();
    doReturn(TARGET).when(PROCEEDING_JOIN_POINT).getTarget();
    doThrow(new RuntimeException()).when(PROCEEDING_JOIN_POINT).proceed();

    assertThrows(
            RuntimeException.class, () -> loggerAspect.logMethod(PROCEEDING_JOIN_POINT));
  }
}
