package com.test.firebasetest.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.test.firebasetest.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {
    ILoginView mLoginView;
    FirebaseAuth mAuth;
    FirebaseUser mCurrentUser;

    public LoginPresenter(ILoginView loginView){
        mLoginView = loginView;
        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = null;
    }
    @Override
    public void doLogin(String username, String password) {
        if (!validateForm(username, password)){
            mLoginView.onLoginResult(false);
            return;
        }
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
                            mLoginView.showErrorMessage(e.getMessage());
                        } else {
                            // call back login result
                            mLoginView.onLoginResult(true);
                        }
                    }
                });
    }

    @Override
    public boolean checkUserSignedIn() {
        mCurrentUser = mAuth.getCurrentUser();
        if(mCurrentUser != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void startNewActivity(Intent intent) {
        mLoginView.startNewActivity(intent);
    }

    @Override
    public void clearAllInput() {
        mLoginView.onClearAllInput();
    }

    private boolean validateForm(String email, String password){
        boolean valid = true;
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            valid = false;
        }
        return valid;
    }
}
