package com.arkenstone.mvvmdemo.repository.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by kiran on 2/13/18.
 */

@Entity(tableName = "user") public class User {

  @PrimaryKey @ColumnInfo(name = "email") private @NonNull String email;

  @ColumnInfo(name = "password") private String password;

  @Ignore public User() {

  }

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
