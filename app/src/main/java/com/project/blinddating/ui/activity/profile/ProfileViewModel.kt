package com.project.blinddating.ui.activity.profile

import com.google.firebase.firestore.auth.User
import com.project.blinddating.helper.FirebaseHelper
import com.project.blinddating.ui.activity.base.BaseViewModel
import com.project.blinddating.ui.activity.register.RegisterViewState
import com.project.blinddating.ui.activity.waiting.WaitingActivity
import com.project.blinddating.util.AppConstants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    firebaseHelper: FirebaseHelper,
    viewState: ProfileViewState
) : BaseViewModel<ProfileViewState>(firebaseHelper, viewState){

    private var email = ""
    private var passwd = ""

    fun setUpEmailPasswd(email : String, passwd: String){
        this.email = email
        this.passwd = passwd
    }

    fun handleSubmitJoinButtonClicked(id:String, name:String, gender:String, age:String, address : String){
        joinWithMoreProfile(id, name, gender, age, address)
    }

    private fun joinWithMoreProfile(id:String, name:String, gender:String, age:String, address : String){
        if (!validate(id,name,gender,age,address)){
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
            val user = com.project.blinddating.entity.User(
                user_id = id, user_name = name, user_gender = gender, user_age = age, user_address = address, user_email = email
            )
            firebaseHelper.addProfile(user)
            viewState.newActivity = WaitingActivity::class
            viewState.clearActivityOnIntent = true
            updateUi()
        }
    }

    private fun validate(id:String, name:String, gender:String, age:String, address : String) : Boolean{
        if (id.isEmpty() || name.isEmpty() || gender.isEmpty() || age.isEmpty() || address.isEmpty()) {
            this.viewState.errorMsg = "Please Fill out all the fields"
            return false
        }

//        if (!isEmailValid(email)){
//            this.viewState.errorMsg = "Please enter a valid email"
//            return false
//        }
//
//        if (password.length<6){
//            this.viewState.errorMsg = "Password must be at least 6 char long"
//            return false
//        }
//
//        if (password != confirmPassword) {
//            this.viewState.errorMsg = "Password confirmation doesn't match"
//            return false
//        }

        viewState.errorMsg = ""
        return true
    }

}