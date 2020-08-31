package com.project.blinddating.ui.activity.login

import com.project.blinddating.di.scope.AppScoped
import com.project.blinddating.helper.FirebaseHelper
import com.project.blinddating.ui.activity.base.BaseViewModel
import com.project.blinddating.ui.activity.register.RegisterActivity
import com.project.blinddating.ui.activity.waiting.WaitingActivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@AppScoped
class LoginViewModel @Inject constructor(
    firebaseHelper: FirebaseHelper,
    viewState: LoginViewState
): BaseViewModel<LoginViewState>(firebaseHelper, viewState) {

    override fun checkUserLoggedIn() {

        if (firebaseHelper.user() != null) {
            viewState.newActivity = WaitingActivity::class
            viewState.clearActivityOnIntent = true
            updateUi()
        }
    }

    fun handleSubmitButtonClicked(username: String, password: String) {
        loginWithEmailAndPassword(username, password)
    }

    fun handleRegisterTextviewClicked() {
        viewState.newActivity = RegisterActivity::class
        updateUi()
    }

    private fun loginWithEmailAndPassword(email: String, password: String) {

        if (!validate(email, password)) {
            updateUi()
            return
        }

        viewState.submitEnabled = false
        updateUi()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
            viewState.errorMessage = "Please enter the correct email and password"
            viewState.submitEnabled = true
            updateUi()
        }

        networkScope.launch(errorHandler) {
            firebaseHelper.login(email, password)
            viewState.newActivity = WaitingActivity::class
            viewState.clearActivityOnIntent = true
            updateUi()
        }
    }

    private fun validate(email: String, password: String): Boolean {

        if (email.isEmpty() || password.isEmpty()) {
            this.viewState.errorMessage = "Please fill out all the fields"
            return false
        }

        if (!isEmailValid(email)) {
            this.viewState.errorMessage = "Please enter a valid email"
            return false
        }

        if (password.length < 6) {
            this.viewState.errorMessage = "Password must be at least 6 characters long"
            return false
        }

        viewState.errorMessage = ""
        return true
    }

    private fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }


}