package com.chhin.fitnesstracker;

public class FTRuntimeException extends RuntimeException {

  public FTRuntimeException() {
  }

  public FTRuntimeException(String message) {
    super(message);
  }

  public FTRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public FTRuntimeException(Throwable cause) {
    super(cause);
  }

  public FTRuntimeException(String message, Throwable cause, boolean enableSuppression,
                            boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
