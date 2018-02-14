package com.arkenstone.mvvmdemo;

import android.app.Application;
import com.arkenstone.mvvmdemo.repository.DataRepository;
import com.arkenstone.mvvmdemo.repository.DataRepositoryImpl;
import com.arkenstone.mvvmdemo.repository.local.UserDatabase;

/**
 * Created by kiran on 2/14/18.
 */

public class MVVMApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();
  }

  public UserDatabase getDatabase() {
    return UserDatabase.getInstance(this);
  }

  public DataRepository getRepository() {
    return DataRepositoryImpl.getInstance(getDatabase());
  }
}
