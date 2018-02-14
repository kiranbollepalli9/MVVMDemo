package com.arkenstone.mvvmdemo.repository.model;

import com.arkenstone.mvvmdemo.repository.local.User;

/**
 * Created by kiran on 2/13/18.
 */

public class LoginResponse {

  User user;

  Throwable error;

  public LoginResponse(User mUser) {
    this.user = mUser;
  }

  public LoginResponse(Throwable error) {
    this.error = error;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User mUser) {
    this.user = mUser;
  }

  public Throwable getError() {
    return error;
  }

  public String getErrorMessage() {
    return (error != null) ? error.getMessage() : null;
  }

  public void setError(Throwable error) {
    this.error = error;
  }
}
