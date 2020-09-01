package com.project.blinddating.ui.activity.register

import android.util.Log
import com.project.blinddating.helper.FirebaseHelper
import com.project.blinddating.ui.activity.base.BaseViewModel
import com.project.blinddating.ui.activity.login.LoginActivity
import com.project.blinddating.ui.activity.profile.ProfileActivity
import com.project.blinddating.ui.activity.waiting.WaitingActivity
import com.project.blinddating.util.AppConstants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    firebaseHelper: FirebaseHelper,
    viewState: RegisterViewState
) : BaseViewModel<RegisterViewState>(firebaseHelper, viewState){

    override fun checkUserLoggedIn() {
        if (firebaseHelper.user() != null){
            viewState.newActivity = WaitingActivity::class
            viewState.clearActivityOnIntent = true
            updateUi()
        }
    }

    fun handleSubmitButtonClicked(username:String, password:String, confirmPassword:String){
        registerWithEmailAndPassword(username,password,confirmPassword)
    }

    fun handleLoginTextviewClicked(){
        viewState.newActivity = LoginActivity::class
        updateUi()
    }

    private fun registerWithEmailAndPassword(username: String, password: String, confirmPassword: String){
        if (!validate(username,password,confirmPassword)){
            updateUi()
            return
        }

        viewState.submitEnabled = false
        updateUi()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
            viewState.errorMsg = "Account associated with this email already exists"
            viewState.submitEnabled = true
            updateUi()
        }

        networkScope.launch(errorHandler) {
            firebaseHelper.register(username, password)
            viewState.newActivity = ProfileActivity::class
            viewState.clearActivityOnIntent = true
            viewState.intentExtras.putString(AppConstants.REGISTER_USEREMAIL, username)
            viewState.intentExtras.putString(AppConstants.REGISTER_USERPASSWORD, password)
            updateUi()
        }
    }

    private fun validate(email:String,password: String,confirmPassword: String) : Boolean{
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            this.viewState.errorMsg = "Please Fill out all the fields"
            return false
        }

        if (!isEmailValid(email)){
            this.viewState.errorMsg = "Please enter a valid email"
            return false
        }

        if (password.length<6){
            this.viewState.errorMsg = "Password must be at least 6 char long"
            return false
        }

        if (password != confirmPassword) {
            this.viewState.errorMsg = "Password confirmation doesn't match"
            return false
        }

        viewState.errorMsg = ""
        return true
    }

    private fun isEmailValid(email: String) : Boolean{
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