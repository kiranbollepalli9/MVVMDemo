package com.arkenstone.mvvmdemo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.database.sqlite.SQLiteConstraintException;
import com.arkenstone.mvvmdemo.repository.local.User;
import com.arkenstone.mvvmdemo.repository.local.UserDatabase;
import com.arkenstone.mvvmdemo.repository.model.LoginResponse;
import com.arkenstone.mvvmdemo.repository.model.RegistrationResponse;
import java.util.List;

/**
 * Created by kiran on 2/13/18.
 */

public class DataRepositoryImpl implements DataRepository {

  private static DataRepository sRepository;
  private UserDatabase mDatabase;

  private DataRepositoryImpl(UserDatabase database) {
    mDatabase = database;
  }

  public static synchronized DataRepository getInstance(UserDatabase database) {
    if (sRepository == null) {
      sRepository = new DataRepositoryImpl(database);
    }
    return sRepository;
  }

  @Override public LiveData<LoginResponse> loginUser(String email, String password) {

    List<User> users = mDatabase.userDao().getUsersByEmail(email);
    MutableLiveData<LoginResponse> liveData = new MutableLiveData<>();

    if (users == null || users.size() == 0) {
      liveData.setValue(new LoginResponse(new Throwable("Invalid Email")));
      return liveData;
    }

    for (User user : users) {
      if (user.getPassword().equals(password)) {
        liveData.setValue(new LoginResponse(user));
        return liveData;
      }
    }
    liveData.setValue(new LoginResponse(new Throwable("Invalid Password")));
    return liveData;
  }

  @Override public LiveData<RegistrationResponse> registerUser(String email, String password) {
    User newUser = new User(email, password);
    MutableLiveData<RegistrationResponse> liveData = new MutableLiveData<>();

    try {
      long id = mDatabase.userDao().insertUser(newUser);
      liveData.setValue(new RegistrationResponse(id));

    }catch (SQLiteConstraintException exception) {
      liveData.setValue(new RegistrationResponse(-1));
    }

    return liveData;
  }

  @Override public LiveData<List<User>> getAllUsers() {
    return mDatabase.userDao().getAllUsers();
  }
}
