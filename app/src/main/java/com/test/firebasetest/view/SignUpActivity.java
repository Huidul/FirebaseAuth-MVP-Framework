package com.test.firebasetest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.firebasetest.R;
import com.test.firebasetest.presenter.ISignUpPresenter;
import com.test.firebasetest.presenter.SignUpPresenter;

public class SignUpActivity extends AppCompatActivity
        implements ISignUpView, View.OnClickListener{
    private EditText mEmail;
    private EditText mPassword;
    private Button submitBtn;
    private Button clearAllBtn;

    private ISignUpPresenter mSignUpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initWidget();

        mSignUpPresenter = new SignUpPresenter(this);

        bindListener();
    }

    private void initWidget() {
        mEmail = (EditText) findViewById(R.id.edt_signUp_email);
        mPassword = (EditText) findViewById(R.id.edt_signUp_password);
        submitBtn = (Button) findViewById(R.id.btn_signUP_submit);
        clearAllBtn = (Button) findViewById(R.id.btn_signUp_clear);
    }


    private void bindListener() {
        submitBtn.setOnClickListener(this);
        clearAllBtn.setOnClickListener(this);
    }

    @Override
    public void onClearAllInput() {
        mEmail.setText("");
        mPassword.setText("");
        submitBtn.setEnabled(true);
        clearAllBtn.setEnabled(true);
    }

    @Override
    public void onSignUpResult(boolean result) {
        if(result){
           Toast.makeText(this, R.string.message_signUp_success, Toast.LENGTH_LONG).show();
           // start and continue other activity
            Intent intent = new Intent();
            intent.setClass(SignUpActivity.this, ShowUserProfileActivity.class);
            mSignUpPresenter.startNewActivity(intent);
        } else {
            Toast.makeText(this, R.string.message_signUp_fail, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void startNewActivity(Intent intent) {
        this.startActivity(intent);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        String emailInput = mEmail.getText().toString();
        String passwordInput = mPassword.getText().toString();
        Log.d("sign up", "email = " + emailInput + "password = " + passwordInput);

        switch (view.getId()){
            case R.id.btn_signUP_submit:
                mSignUpPresenter.doSignUp(emailInput, passwordInput);
                break;
            case R.id.btn_signUp_clear:
                mSignUpPresenter.clearAll();
                break;
        }
    }
}
