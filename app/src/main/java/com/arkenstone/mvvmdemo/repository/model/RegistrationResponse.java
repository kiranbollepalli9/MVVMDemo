package com.arkenstone.mvvmdemo.repository.model;

/**
 * Created by kiran on 2/13/18.
 */

public class RegistrationResponse {

  Throwable error;

  boolean isSuccess;

  //TODO: Need to revisit this code.
  public RegistrationResponse(long id) {
    if (id == -1) {
      error = new Throwable("Unable to insert new user");
      isSuccess = false;
    } else {
      isSuccess = true;
    }
  }

  public boolean isSuccess() {
    return isSuccess;
  }

  public void setSuccess(boolean success) {
    isSuccess = success;
  }

  public RegistrationResponse(Throwable error) {
    this.error = error;
  }

  public Throwable getError() {
    return error;
  }

  public void setError(Throwable error) {
    this.error = error;
  }
}
