package com.test.firebasetest.presenter;

import android.content.Intent;

import com.test.firebasetest.model.User;

public interface IShowUserAccountPresenter extends IPresenterRoot{
    public String getUsername();
    public void doSignOut(Intent intent);
}
