package com.qbs.app.common.exception;

/**
 * Exception thrown for unknown application errors.
 */
public class PostException extends RuntimeException {
  /**
   * Creates exception with given message.
   *
   * @param message the message
   */
  public PostException(final String message) {
    super(message);
  }

  /**
   * Creates exception with given message and cause.
   *
   * @param message the message
   * @param cause the cause
   */
  public PostException(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Creates exception with given cause.
   *
   * @param cause the cause
   */
  public PostException(final Throwable cause) {
    super(cause);
  }
}