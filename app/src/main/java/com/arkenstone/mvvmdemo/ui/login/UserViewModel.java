package com.arkenstone.mvvmdemo.ui.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.arkenstone.mvvmdemo.MVVMApplication;
import com.arkenstone.mvvmdemo.repository.DataRepository;
import com.arkenstone.mvvmdemo.repository.local.User;
import com.arkenstone.mvvmdemo.repository.model.LoginResponse;
import com.arkenstone.mvvmdemo.repository.model.RegistrationResponse;
import java.util.List;

/**
 * Created by kiran on 2/13/18.
 */

public class UserViewModel extends AndroidViewModel {

  DataRepository mRepository;
  MediatorLiveData<List<User>> mAllUsers;

  public UserViewModel(@NonNull Application application) {
    super(application);
    mRepository = ((MVVMApplication) application).getRepository();

    mAllUsers = new MediatorLiveData<>();
  }


  public MediatorLiveData<List<User>> getAllUsersResponse() {
    return mAllUsers;
  }

  void getAllUsers() {
    mAllUsers.addSource(mRepository.getAllUsers(), new Observer<List<User>>() {
      @Override public void onChanged(@Nullable List<User> users) {
        mAllUsers.setValue(users);
      }
    });
  }

  LiveData<LoginResponse> loginUser(String email, String password) {
    return mRepository.loginUser(email, password);
  }

  LiveData<RegistrationResponse> registerUser(String email, String password) {
    return mRepository.registerUser(email, password);

  }
}

