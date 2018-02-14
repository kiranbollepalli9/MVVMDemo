package com.arkenstone.mvvmdemo.repository;

import android.arch.lifecycle.LiveData;
import com.arkenstone.mvvmdemo.repository.local.User;
import com.arkenstone.mvvmdemo.repository.model.LoginResponse;
import com.arkenstone.mvvmdemo.repository.model.RegistrationResponse;
import java.util.List;

/**
 * Created by kiran on 2/13/18.
 */

public interface DataRepository {

  LiveData<LoginResponse> loginUser(String email, String password);

  LiveData<RegistrationResponse> registerUser(String email, String password);

  LiveData<List<User>> getAllUsers();
}
