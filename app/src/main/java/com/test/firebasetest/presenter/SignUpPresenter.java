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
import com.test.firebasetest.R;
import com.test.firebasetest.view.ISignUpView;

public class SignUpPresenter implements ISignUpPresenter {
    private ISignUpView mView;
    private FirebaseAuth mAuth;

    public SignUpPresenter(ISignUpView view) {
        mView = view;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void doSignUp(String email, String password) {
        if (!validateForm(email, password)){
            mView.onSignUpResult(false);
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    FirebaseAuthException e = (FirebaseAuthException)task.getException();
                    mView.showErrorMessage(e.getMessage());
                } else {
                    mView.onSignUpResult(true);
                }
            }
        });
    }

    @Override
    public void clearAll() {
        mView.onClearAllInput();
    }

    @Override
    public void startNewActivity(Intent intent) {
        mView.startNewActivity(intent);
    }

    private boolean validateForm(String email, String password){
        boolean valid = true;
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            valid = false;
        }
        return valid;
    }
}
