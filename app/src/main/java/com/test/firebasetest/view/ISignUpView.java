package com.test.firebasetest.view;

public interface ISignUpView extends IUIViewRoot {
    public void onClearAllInput();
    public void onSignUpResult(boolean result);
    public void showErrorMessage(String errorMessage);
}
