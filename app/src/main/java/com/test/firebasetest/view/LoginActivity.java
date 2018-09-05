package com.test.firebasetest.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.firebasetest.R;
import com.test.firebasetest.presenter.ILoginPresenter;
import com.test.firebasetest.presenter.LoginPresenter;

/**
 * This class define login Activity
 */
@SuppressLint("Registered")
public class LoginActivity extends AppCompatActivity
        implements ILoginView, View.OnClickListener{
    private EditText mUsername;
    private EditText mPassword;
    private Button loginBtn;
    private Button clearBtn;
    private Button signUpBtn;

    ILoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // load login layout xml file
        setContentView(R.layout.activity_login);
        initWidget();
        bindListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // check if there is already a user signed in
        if(mLoginPresenter.checkUserSignedIn()){
            // ......
            onLoginResult(true);
        }
    }

    /**
     * Initialize Activity view
     */
    private void initWidget() {
        mUsername = (EditText) findViewById(R.id.edt_login_username);
        mPassword = (EditText) findViewById(R.id.edt_login_password);
        loginBtn  = (Button) findViewById(R.id.btn_login);
        clearBtn = (Button) findViewById(R.id.btn_login_clear);
        signUpBtn = (Button) findViewById(R.id.btn_login_signUp);
        mLoginPresenter = new LoginPresenter(this);
    }

    /**
     * Bind each clickable widget with event
     */
    private void bindListener() {
        loginBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);

    }

    @Override
    public void onLoginResult(boolean result) {
        if(result){
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, ShowUserProfileActivity.class);
            Toast.makeText(this, R.string.message_login_success, Toast.LENGTH_LONG).show();
            // switch to user account home
            mLoginPresenter.startNewActivity(intent);
        } else {
            Toast.makeText(this, R.string.message_login_fail, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClearAllInput() {
        mUsername.setText("");
        mPassword.setText("");
        Toast.makeText(this, R.string.message_clear_input, Toast.LENGTH_LONG);
        loginBtn.setEnabled(true);
        clearBtn.setEnabled(true);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    public void startNewActivity(Intent intent){
        this.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        String usernameInput = mUsername.getText().toString();
        String passwordInput = mPassword.getText().toString();

        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, SignUpActivity.class);

        switch (viewId){
            case R.id.btn_login:
                mLoginPresenter.doLogin(usernameInput, passwordInput);
                break;
            case R.id.btn_login_signUp:
                mLoginPresenter.startNewActivity(intent);
                break;
            case R.id.btn_login_clear:
                mLoginPresenter.clearAllInput();
                break;
        }
    }
}
