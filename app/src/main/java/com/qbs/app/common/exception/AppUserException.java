package com.qbs.app.common.exception;

/** Exception thrown for unknown application errors. */
public class AppUserException extends RuntimeException {
  /**
   * Creates exception with given message.
   *
   * @param message the message
   */
  public AppUserException(final String message) {
    super(message);
  }

  /**
   * Creates exception with given message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public AppUserException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates exception with given cause.
   *
   * @param cause the cause
   */
  public AppUserException(final Throwable cause) {
    super(cause);
  }
}
