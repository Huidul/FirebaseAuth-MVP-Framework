package com.test.firebasetest.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.firebasetest.R;
import com.test.firebasetest.presenter.IShowUserAccountPresenter;
import com.test.firebasetest.presenter.ShowUserProfilePresenter;

public class ShowUserProfileActivity extends AppCompatActivity
        implements IShowUserAccountView, View.OnClickListener {
    TextView mUsernameContent;
    Button signOutBtn;

    IShowUserAccountPresenter mSUAPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);
        initWidget();
        bindListener();
        mSUAPresenter = new ShowUserProfilePresenter(this);
    }

    private void bindListener() {
        signOutBtn.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        String username = mSUAPresenter.getUsername();
        mUsernameContent.setText(username);
    }

    private void initWidget() {
        mUsernameContent = (TextView) findViewById(R.id.txt_showUser_usernameContent);
        signOutBtn = (Button) findViewById(R.id.btn_showUser_signOut);
    }

    @Override
    public void startNewActivity(Intent intent) {
        this.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(ShowUserProfileActivity.this, LoginActivity.class);

        switch (view.getId()){
            case R.id.btn_showUser_signOut:
                mSUAPresenter.doSignOut(intent);
        }
    }
}
