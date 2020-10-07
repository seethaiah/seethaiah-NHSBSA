package com.seeta.common.framework.cucumber.web.core;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomException.
 */
public class CustomException extends RuntimeException implements Serializable {

  /** This is a customized exception which overrides the runtime exception, to differentiate reporting exception. */

  private static final long serialVersionUID = -3644742957748395150L;

  /**
   * Instantiates a new custom exception.
   */
  public CustomException() {
    super();
  }

  /**
   * Instantiates a new custom exception.
   *
   * @param msg the msg
   */
  public CustomException(String msg) {
    super(msg);
  }

  /**
   * For wrapping up exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public CustomException(String message, Throwable cause) {
    super(message, cause);
  }

}	
