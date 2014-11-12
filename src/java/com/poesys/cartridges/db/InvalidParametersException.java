/*
 * Copyright (c) 2008 Poesys Associates. All rights reserved.
 */
package com.poesys.cartridges.db;

/**
 * An exception that indicates that a method received parameters that were not
 * correct in some way.
 * 
 * @author Robert J. Muller
 */
public class InvalidParametersException extends Exception {

  /**
   * Generated serial version UID for serializable exception class
   */
  private static final long serialVersionUID = -1308724992241165349L;

  /**
   * Create a InvalidParametersException object.
   * @param message
   */
  public InvalidParametersException(String message) {
    super(message);
  }

  /**
   * Create a InvalidParametersException object.
   * @param cause
   */
  public InvalidParametersException(Throwable cause) {
    super(cause);
  }

  /**
   * Create a InvalidParametersException object.
   * @param message
   * @param cause
   */
  public InvalidParametersException(String message, Throwable cause) {
    super(message, cause);
  }
}
