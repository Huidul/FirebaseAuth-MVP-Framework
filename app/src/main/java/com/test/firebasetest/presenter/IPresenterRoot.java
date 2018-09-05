package com.test.firebasetest.presenter;

import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public interface IPresenterRoot {
    public void startNewActivity(Intent intent);
}
