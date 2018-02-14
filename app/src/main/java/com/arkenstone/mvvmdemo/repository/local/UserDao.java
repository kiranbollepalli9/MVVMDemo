package com.arkenstone.mvvmdemo.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;

/**
 * Created by kiran on 2/14/18.
 */

@Dao public interface UserDao {

  @Insert long[] insertAll(User... users);

  @Insert(onConflict = OnConflictStrategy.FAIL) long insertUser(User user);

  @Query("SELECT * FROM user WHERE email LIKE :email")
  List<User> getUsersByEmail(String email);

  @Query("SELECT * FROM user") LiveData<List<User>> getAllUsers();
}
