package com.wholesale.specialintegration.exception;

/**
 * Standard result codes for ServiceStatus entity
 */
public enum ResultCodes {
  OK(200, "OK"), ERROR(500, "Internal Server Error"), ERROR_PARAMETERS(400, "Bad Request"),
  BUSINESS_ERROR(422, "Unprocessable Entity"), CONNECT_TIMEOUT(504, "Gateway Timeout"),
  NOT_FOUND(404, "Corporate not found");

  private final int code;
  private final String message;

  ResultCodes(int i, String message) {
    this.code = i;
    this.message = message;
  }

  /**
   * Returns the code
   * 
   * @return the code
   */
  public int getCode() {
    return this.code;
  }

  /**
   * Returns the message
   * 
   * @return the message
   */
  public String getMessage() {
    return this.message;
  }
}
