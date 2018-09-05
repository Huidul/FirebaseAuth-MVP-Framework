package com.test.firebasetest.presenter;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface ILoginPresenter extends IPresenterRoot{
    public void doLogin(String username, String password);
    public void clearAllInput();
    public boolean checkUserSignedIn();
}
