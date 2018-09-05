package com.test.firebasetest.presenter;

import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.firebasetest.view.IShowUserAccountView;

public class ShowUserProfilePresenter implements IShowUserAccountPresenter {
    IShowUserAccountView mView;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    public ShowUserProfilePresenter(IShowUserAccountView view) {
        mView = view;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public String getUsername() {
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            return currentUser.getEmail();
        } else {
            return "fail to load user."; // test
        }
    }

    @Override
    public void doSignOut(Intent intent) {
        mAuth.signOut();
        // switch to login activity ui
        this.startNewActivity(intent);
    }

    @Override
    public void startNewActivity(Intent intent) {
        mView.startNewActivity(intent);
    }
}
