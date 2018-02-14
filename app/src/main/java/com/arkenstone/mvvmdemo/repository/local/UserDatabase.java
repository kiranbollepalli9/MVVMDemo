package com.arkenstone.mvvmdemo.repository.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by kiran on 2/14/18.
 */

@Database(entities = { User.class }, version = 4)
public abstract class UserDatabase
    extends RoomDatabase {

  private static UserDatabase sDatabase;

  public abstract UserDao userDao();

  public static synchronized UserDatabase getInstance(Context context) {
    if (sDatabase == null) {
      sDatabase =
          Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user.db")
              .fallbackToDestructiveMigration()
              .allowMainThreadQueries() //FIXME: Need to revisit this code.
              .build();
    }
    return sDatabase;
  }

  public void cleanup() {
    sDatabase = null;
  }
}
