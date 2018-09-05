package com.test.firebasetest.view;

public interface ILoginView extends IUIViewRoot {
    public void onLoginResult(boolean result);
    public void onClearAllInput();
    public void showErrorMessage(String errorMessage);
}
