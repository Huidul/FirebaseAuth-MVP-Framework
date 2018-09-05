package com.test.firebasetest.presenter;

import android.content.Intent;

public interface ISignUpPresenter extends IPresenterRoot{
    public void doSignUp(String email, String password);
    public void clearAll();
   // public void startNewActivity(Intent intent);
}
