package com.project.blinddating.ui.activity.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.blinddating.helper.FirebaseHelper
import com.project.blinddating.ui.activity.login.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel<S:ViewState> (
    val firebaseHelper: FirebaseHelper, var viewState: S) : ViewModel() {

    protected val stateLiveData = MutableLiveData<ViewState>()

    private val networkJob = Job()
    protected val networkScope = CoroutineScope(Dispatchers.IO + networkJob)

    open fun checkUserLoggedIn() {
        if (firebaseHelper.user() == null){
            viewState.newActivity = LoginActivity::class
            viewState.clearActivityOnIntent = true
            updateUi()
        }
    }

    open fun goToLogin(){
        viewState.newActivity = LoginActivity::class
        //viewState.clearActivityOnIntent = true
        updateUi()
    }

    fun handleSignOut(){
        firebaseHelper.auth.signOut()
        checkUserLoggedIn()
    }

    fun getState() : MutableLiveData<ViewState>{
        return this.stateLiveData
    }

    fun resetNewActivity() {
        viewState.newActivity = null
        updateUi()
    }

    fun updateUi(){
        stateLiveData.postValue(viewState)
    }

}