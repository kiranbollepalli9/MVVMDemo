package com.arkenstone.mvvmdemo.ui.login;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.arkenstone.mvvmdemo.R;
import com.arkenstone.mvvmdemo.repository.model.LoginResponse;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

  UserViewModel mUserViewModel;

  Button mCreateAccountButton, mLoginButton;

  EditText mEmailView, mPasswordView;

  private static final String PASSWORD_PATTERN =
      "(?=(?:.*[a-z]){1,})(?=(?:.*[A-Z]){1,})(?=(?:.*[0-9]){1,})^[a-zA-Z0-9]*$";
  private Pattern mPasswordPattern;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    initialize();
  }

  private void initialize() {
    mPasswordPattern = Pattern.compile(PASSWORD_PATTERN);
    mCreateAccountButton = this.findViewById(R.id.button_create_account);
    mCreateAccountButton.setOnClickListener(this);

    mLoginButton = this.findViewById(R.id.button_login);
    mLoginButton.setOnClickListener(this);

    mEmailView = this.findViewById(R.id.text_email);
    mPasswordView = this.findViewById(R.id.text_password);

    mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    initViewModelSubscribers();
  }

  private void initViewModelSubscribers() {

    mUserViewModel.getAllUsersResponse()
        .observe(this, users -> Log.i("LoginActivity", " users count " + users.size()));
  }

  @Override public void onClick(View view) {
    String email = mEmailView.getEditableText().toString();
    String password = mPasswordView.getEditableText().toString();
    if (!validate(email, password)) {
      return;
    }
    switch (view.getId()) {
      case R.id.button_create_account:

        mUserViewModel.registerUser(email, password).observe(this, response -> {
          if (response.getError() != null) {
            showAlertDialog(getString(R.string.dialog_title_alert),
                response.getError().getMessage());
          } else {
            showAlertDialog(R.string.dialog_title_alert,
                R.string.dialog_message_registration_success);
            //mUserViewModel.getAllUsers();
          }
        });
        break;
      case R.id.button_login:

        mUserViewModel.loginUser(email, password).observe(this, new Observer<LoginResponse>() {
          @Override public void onChanged(@Nullable LoginResponse response) {
            if (response.getError() != null) {
              Log.i("LoginActivity", " Error : " + response.getErrorMessage());
              showAlertDialog(getString(R.string.dialog_title_alert),
                  response.getError().getMessage());
            } else {
              showAlertDialog(R.string.dialog_title_alert, R.string.dialog_message_login_success);
            }
          }
        });
        break;
    }
  }

  void showAlertDialog(int titleId, int messageId) {
    showAlertDialog(getString(titleId), getString(messageId));
  }

  void showAlertDialog(String title, String message) {
    new AlertDialog.Builder(this).setTitle(title)
        .setMessage(message)
        .setNeutralButton(android.R.string.ok, null)
        .show();
  }

  boolean validate(String email, String password) {
    if (email == null || email.isEmpty()) {
      mEmailView.setError(getString(R.string.error_invalid_field));
      return false;
    }

    if (password == null || password.isEmpty()) {
      mPasswordView.setError(getString(R.string.error_invalid_field));
      return false;
    }

    if (password.length() < 8) {
      mPasswordView.setError(getString(R.string.error_invalid_password_length));
      return false;
    }

    //Validate Password
    if (!mPasswordPattern.matcher(password).matches()) {
      mPasswordView.setError(getString(R.string.error_invalid_password_chars));
      return false;
    }
    return true;
  }
}
